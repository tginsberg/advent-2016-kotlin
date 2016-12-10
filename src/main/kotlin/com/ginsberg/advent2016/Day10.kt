/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 10: December 10, 2016
 *
 * From http://adventofcode.com/2016/day/10
 *
 */
class Day10(input: List<String>, val find: Set<Int> = setOf(17, 61)) {
    companion object {
        val giveChip: Regex = Regex("""^value (\d+) goes to bot (\d+)$""")
        val operation = Regex("""^bot (\d+) gives low to (bot|output) (\d+) and high to (bot|output) (\d+)$""")
    }

    val bots = mutableMapOf<String, ChipHolder.Bot>()
    val outputs = mutableMapOf<String, ChipHolder.Output>()
    val watched = mutableSetOf<ChipHolder.Bot>()

    val watch: (ChipHolder.Bot) -> Unit = {
        if (it.inventory.containsAll(find)) {
            watched.add(it)
        }
    }

    init {
        input.forEach { executeCommand(it) }
    }

    fun solvePart1(): String {
        runCommands()
        return watched.first().id
    }

    fun solvePart2(): Int {
        runCommands()
        return outputs["0"]?.inventory?.first()!! *
            outputs["1"]?.inventory?.first()!! *
            outputs["2"]?.inventory?.first()!!
    }

    fun bot(id: String): ChipHolder.Bot =
        bots.getOrPut(id) { ChipHolder.Bot(id, watch) }

    fun output(id: String): ChipHolder.Output =
        outputs.getOrPut(id) { ChipHolder.Output(id) }

    private fun executeCommand(command: String) {
        when {
            giveChip.matches(command) -> {
                val (chip, bot) = giveChip.matchEntire(command)!!.destructured
                bot(bot).take(chip.toInt())

            }
            operation.matches(command) -> {
                val (fromBot, lowType, lowId, highType, highId) = operation.matchEntire(command)!!.destructured
                bot(fromBot).lowTo = if (lowType == "bot") bot(lowId) else output(lowId)
                bot(fromBot).highTo = if (highType == "bot") bot(highId) else output(highId)

            }
            else -> println("Invalid command $command, skipping")
        }
    }

    private fun runCommands() {
        var somethingCanAct = true
        while (somethingCanAct) {
            somethingCanAct = tick()
        }
    }

    private fun tick(): Boolean {
        val willAct = bots.values.filter { it.canAct() }
        willAct.forEach { it.act() }
        return !willAct.isEmpty()
    }

    sealed class ChipHolder(val id: String) {
        val inventory = mutableSetOf<Int>()

        fun take(chip: Int) {
            inventory.add(chip)
        }

        class Output(id: String) : ChipHolder(id) {
        }

        class Bot(id: String, val watcher: (Bot) -> Unit) : ChipHolder(id) {
            var lowTo: ChipHolder? = null
            var highTo: ChipHolder? = null

            fun canAct(): Boolean =
                lowTo != null && highTo != null && inventory.size == 2

            fun act() {
                watcher(this)
                lowTo?.take(inventory.min()!!)
                highTo?.take(inventory.max()!!)
                inventory.clear()
            }
        }
    }
}
