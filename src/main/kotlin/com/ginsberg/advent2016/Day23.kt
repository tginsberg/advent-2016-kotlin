/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.assembunny.AssembunnyParser
import com.ginsberg.advent2016.assembunny.AssembunnyState


/**
 * Advent of Code - Day 23: December 23, 2016
 *
 * From http://adventofcode.com/2016/day/23
 *
 */
class Day23(input: List<String>) {

    val instructions = AssembunnyParser.parseInstructions(input)

    fun solvePart1(): Int =
        solve(mapOf('a' to 7, 'b' to 0, 'c' to 0, 'd' to 0))

    fun solvePart2(): Int =
        solve(mapOf('a' to 12))

    private fun solve(registers: Map<Char, Int>): Int {
        var state = AssembunnyState(instructions, registers)
        while (!state.isDone()) {
            state = state.op()
        }
        return state.registers['a'] ?: 0
    }
}
