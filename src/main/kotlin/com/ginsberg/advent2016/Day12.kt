/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.assembunny.AssembunnyParser
import com.ginsberg.advent2016.assembunny.AssembunnyState


/**
 * Advent of Code - Day 12: December 12, 2016
 *
 * From http://adventofcode.com/2016/day/12
 *
 */
class Day12(input: List<String>) {

    val instructions = AssembunnyParser.parseInstructions(input)

    fun solvePart1(): Int =
        solve("abcd".map { it to 0 }.toMap())

    fun solvePart2(): Int =
        solve("abcd".map { it to 0 }.toMap().plus('c' to 1))

    private fun solve(registers: Map<Char, Int>): Int {
        var state = AssembunnyState(instructions, registers)
        while (!state.isDone()) {
            state = state.op()
        }
        return state.registers['a'] ?: 0
    }

}
