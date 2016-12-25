/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.assembunny.AssembunnyParser
import com.ginsberg.advent2016.assembunny.AssembunnyState


/**
 * Advent of Code - Day 25: December 25, 2016
 *
 * From http://adventofcode.com/2016/day/25
 *
 */
class Day25(input: List<String>) {
    val instructions = AssembunnyParser.parseInstructions(input)
    val initialRegisters = "abcd".map { it to 0 }.toMap()

    fun solvePart1(): Int =
        generateSequence(0, Int::inc)
            .filter { outputForRegisterValue(it) in setOf("0101010101", "1010101010") }
            .first()

    private fun outputForRegisterValue(a: Int, len: Int = 10): String =
        stateSequence(initialRegisters.plus('a' to a))
            .take(len)
            .joinToString("")

    private fun stateSequence(registers: Map<Char, Int>): Sequence<Int?> =
        generateSequence(
            runUntilOutputOrEnd(AssembunnyState(instructions, registers)),
            { runUntilOutputOrEnd(it) }
        ).map { it.output }

    private fun runUntilOutputOrEnd(s: AssembunnyState, maxCycles: Int = 100000): AssembunnyState {
        var myState = s
        var cycles = 0
        do {
            myState = myState.op()
            cycles = cycles.inc()
        } while (!myState.isDone() && myState.output == null && cycles < maxCycles)
        return myState
    }

}
