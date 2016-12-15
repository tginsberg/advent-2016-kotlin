/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 15: December 15, 2016
 *
 * From http://adventofcode.com/2016/day/15
 *
 */
class Day15(input: List<String>) {
    companion object {
        val DISC_REGEX = Regex("""Disc #(\d+) has (\d+) positions; at time=0, it is at position (\d+).""")
    }

    val discs: List<Disc> = parseInput(input)

    fun solvePart1(): Int = solve()

    fun solvePart2(): Int = solve(discs + Disc(11, 0, discs.size+1))

    private fun solve(state: List<Disc> = discs): Int =
        generateSequence(0, Int::inc)
            .filter { time -> state.all { it.openAtDropTime(time) } }
            .first()

    data class Disc(val positions: Int, val start: Int, val depth: Int) {
        fun openAtDropTime(time: Int): Boolean =
            (time + depth + start) % positions == 0
    }

    private fun parseInput(input: List<String>): List<Disc> =
        input
            .map { DISC_REGEX.matchEntire(it) }
            .filterNotNull()
            .map { it.destructured }
            .map { Disc(it.component2().toInt(), it.component3().toInt(), it.component1().toInt())}
            .sortedBy { it.depth }
}
