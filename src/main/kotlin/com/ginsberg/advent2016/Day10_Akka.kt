/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.UntypedActor
import akka.pattern.Patterns
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import kotlin.reflect.KClass


/**
 * Advent of Code - Day 10: December 10, 2016
 *
 * From http://adventofcode.com/2016/day/10
 *
 * This version treats the Bots as actors using Akka. Probably more code than is needed, but
 * this problem struck me as a fun way to play with Akka Actors and Kotlin.
 *
 */
class Day10_Akka(val input: List<String>, val find: Set<Int> = setOf(17, 61)) {
    companion object {
        val giveChip: Regex = Regex("""^value (\d+) goes to bot (\d+)$""")
        val operation = Regex("""^bot (\d+) gives low to (bot|output) (\d+) and high to (bot|output) (\d+)$""")
    }

    private val system = ActorSystem.create()
    private val bots: MutableMap<Int, ActorRef> = mutableMapOf()
    private val bins: MutableMap<Int, ActorRef> = mutableMapOf()
    private var reporter: ActorRef? = null

    fun solvePart1(): Int {
        reporter = system.actorOf(Props.create(AnswerPart1Actor::class.java, find), "answers1")
        return solve()
    }

    fun solvePart2(): Int {
        reporter = system.actorOf(Props.create(AnswerPart2Actor::class.java), "answers2")
        return solve()
    }

    fun solve(): Int {
        input.map { executeCommand(it) }
        return (Await.result(Patterns.ask(reporter, AnswerInterest, 1000), Duration.Inf()) as Result).answer
    }

    fun shutdown() {
        system.terminate()
    }

    private fun executeCommand(command: String) {
        when {
            giveChip.matches(command) -> {
                val (chip, bot) = giveChip.matchEntire(command)!!.destructured
                findActor(bot.toInt(), bots, BotActor::class).tell(Chip(chip.toInt()), ActorRef.noSender())
            }
            operation.matches(command) -> {
                val (from, lowType, lowId, highType, highId) = operation.matchEntire(command)!!.destructured
                val fromBot = findActor(from.toInt(), bots, BotActor::class)
                fromBot.tell(
                    GiveInstruction(Slot.Low,
                        if (lowType == "bot") {
                            findActor(lowId.toInt(), bots, BotActor::class)
                        } else {
                            findActor(lowId.toInt(), bins, BinActor::class)
                        }
                    ), ActorRef.noSender()
                )
                fromBot.tell(
                    GiveInstruction(Slot.High,
                        if (highType == "bot") {
                            findActor(highId.toInt(), bots, BotActor::class)
                        } else {
                            findActor(highId.toInt(), bins, BinActor::class)
                        })
                    , ActorRef.noSender()
                )
            }
            else -> println("Invalid command $command, skipping")
        }
    }

    private fun findActor(id: Int, map: MutableMap<Int, ActorRef>, type: KClass<*>): ActorRef {
        if (id !in map) {
            map.plusAssign(id to system.actorOf(Props.create(type.java, id, reporter!!), "${type.java.canonicalName}$id"))
        }
        return map[id]!!
    }


    // Bot Management/Instructions
    enum class Slot { High, Low }
    data class Chip(val chip: Int)
    data class GiveInstruction(val slot: Slot, val output: ActorRef)
    data class InventoryReport(val inventory: List<Int>, val id: Int, val type: String)
    object AnswerInterest
    data class Result(val answer: Int)

    // Bot that holds chips, reports its work, and hands chips off.
    class BotActor(val id: Int, val report: ActorRef) : UntypedActor() {
        private var lowOutput: ActorRef? = null
        private var highOut: ActorRef? = null
        private val chips = mutableListOf<Int>()

        override fun onReceive(message: Any?) {
            when (message) {
                is Chip -> {
                    chips.add(message.chip)
                    chips.sort()
                }
                is GiveInstruction -> {
                    if (message.slot == Slot.Low) {
                        lowOutput = message.output
                    } else {
                        highOut = message.output
                    }
                }
                else -> println("Got invalid message, ignoring: $message")
            }
            maybeWork()
        }

        private fun maybeWork() {
            if (lowOutput != null && highOut != null && chips.size == 2) {
                lowOutput?.tell(Chip(chips.first()), self())
                highOut?.tell(Chip(chips.last()), self())
                report.tell(InventoryReport(chips.toList(), id, "bot"), self())
                chips.clear()
            }
        }
    }

    // Bin that holds chips and reports its inventory.
    class BinActor(val id: Int, val report: ActorRef) : UntypedActor() {
        private val chips = mutableListOf<Int>()
        override fun onReceive(message: Any?) {
            when (message) {
                is Chip -> {
                    chips.add(message.chip)
                    report.tell(InventoryReport(chips, id, "bin"), self())
                }
                else -> println("Got invalid message, ignoring: $message")
            }
        }
    }

    abstract class AnswerActor : UntypedActor() {
        var waitingOnAnswer: ActorRef? = null
        var answer: Int? = null
        fun maybeAnswer() {
            if (waitingOnAnswer != null && answer != null) {
                waitingOnAnswer?.tell(Result(answer!!), self())
            }
        }
    }

    class AnswerPart1Actor(val waitFor: Set<Int>) : AnswerActor() {
        override fun onReceive(message: Any?) {
            when (message) {
                is AnswerInterest -> {
                    waitingOnAnswer = sender
                }
                is InventoryReport -> {
                    if (message.type == "bot" && waitFor.containsAll(message.inventory)) {
                        answer = message.id
                    }
                }
                else -> println("Got invalid message, ignoring: $message")
            }
            maybeAnswer()
        }

    }

    class AnswerPart2Actor() : AnswerActor() {
        var totals: List<Int> = listOf()
        override fun onReceive(message: Any?) {
            when (message) {
                is AnswerInterest -> {
                    waitingOnAnswer = sender
                }
                is InventoryReport -> {
                    if (message.type == "bin" && message.id in (0..2)) {
                        totals += message.inventory.first()
                        if (totals.size == 3) {
                            answer = totals.reduce { a, b -> a * b }
                        }
                    }
                }
                else -> println("Got invalid message, ignoring: $message")
            }
            maybeAnswer()
        }
    }
}
