/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 18: December 18, 2016
 *
 * From http://adventofcode.com/2016/day/18
 *
 */
class Day18(val input: String) {

    fun solve(rows: Int): Int =
        rows(input).take(rows).map { it.count { it == '.' } }.sum()

    fun rows(start: String): Sequence<String> =
        generateSequence(start, { nextRow(".$it.") })

    private fun nextRow(row: String): String =
        (0..row.length - 3)
            .map { if (row[it] != row[it + 2]) '^' else '.' }
            .joinToString("")
}
