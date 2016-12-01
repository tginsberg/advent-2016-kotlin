/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Movement

/**
 * Advent of Code - Day 1: December 1, 2016
 *
 * From http://adventofcode.com/2016/day/1
 *
 * You're airdropped near Easter Bunny Headquarters in a city somewhere. "Near",
 * unfortunately, is as close as you can get - the instructions on the Easter
 * Bunny Recruiting Document the Elves intercepted start here, and nobody
 * had time to work them out further.
 */
class Day01(instructionText: String) {

    private val instructions = stringToInstructions(instructionText)

    /**
     * Problem:
     * The Document indicates that you should start at the given coordinates
     * (where you just landed) and face North. Then, follow the provided sequence:
     * either turn left (L) or right (R) 90 degrees, then walk forward the given
     * number of blocks, ending at a new intersection.
     *
     * There's no time to follow such ridiculous instructions on foot, though,
     * so you take a moment and work out the destination. Given that you can only
     * walk on the street grid of the city, how far is the shortest path to the destination?
     *
     * [See tests for examples]
     *
     * How many blocks away is Easter Bunny HQ?
     *
     * Solution:
     * Execute instructions (turn and move) until we run out, calculate the distance.
     */
    fun solvePart1(): Int {
        return instructions
                .fold(Movement.start()) { carry, next -> carry.turn(next.direction).move(next.amount) }
                .distance()
    }

    /**
     * Problem:
     * Then, you notice the instructions continue on the back of the Recruiting Document.
     * Easter Bunny HQ is actually at the first location you visit twice.
     *
     * For example, if your instructions are R8, R4, R4, R8, the first location you visit
     * twice is 4 blocks away, due East.
     *
     * How many blocks away is the first location you visit twice?
     *
     * Solution:
     * Turn the list of instructions into step-by-step instructions. Keep executing
     * steps recursively and collect the locations we've been to. As soon as we detect
     * we've been somewhere (location is in the set), calculate the distance and return.
     */
    fun solvePart2(): Int {
        tailrec fun inner(beenTo: Set<Pair<Int,Int>>, path: List<Instruction>, location: Movement): Int {
            if(beenTo.contains(location.location())) {
                return location.distance()
            } else if(path.isEmpty()) {
                return 0
            }
            val next = location.turn(path[0].direction).move(path[0].amount)
            return inner(beenTo + location.location(), path.drop(1), next)
        }
        return inner(emptySet(), instructions.flatMap { instructionToSteps(it) }, Movement.start())
    }

    /**
     * Given a String of instructions separated by commas, turn them
     * into a list of Instruction objects.
     *
     * NOTES: I probably could have done a Pair<Char,Int> instead of a data class,
     *        but I felt that looked messy. Same thing with consuming
     *        the input String as a stream, I didn't feel the code to do it was
     *        clean and easy enough to follow.
     */
    private fun stringToInstructions(s: String): List<Instruction> =
            s.split(", ").map { Instruction(it[0], it.substring(1).toInt()) }

    private fun instructionToSteps(instruction: Instruction): List<Instruction> =
        listOf(instruction.copy(amount = 1)) + (1 until instruction.amount).map { Instruction(amount = 1)}

    private data class Instruction(val direction: Char = 'X', val amount: Int = 0)
}
