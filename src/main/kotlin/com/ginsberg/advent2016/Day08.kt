/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 8: December 8, 2016
 *
 * From http://adventofcode.com/2016/day/8
 *
 * You come across a door implementing what you can only assume is an implementation of two-factor
 * authentication after a long game of requirements telephone.
 *
 * To get past the door, you first swipe a keycard (no problem; there was one on a nearby desk).
 * Then, it displays a code on a little screen, and you type that code on a keypad. Then, presumably,
 * the door unlocks.
 *
 * Unfortunately, the screen has been smashed. After a few minutes, you've taken everything apart and
 * figured out how it works. Now you just have to work out what the screen would have displayed.
 *
 * [See Unit Test]
 *
 */
class Day08(input: List<String>, val height: Int = 6, val width: Int = 50) {

    private val digits = Regex("""^\D*(\d+)\D*(\d+)\D*$""")
    private val screen = Array(height) { Array(width, { false }) }

    init {
        input.forEach { interpretCommand(it) }
    }

    /**
     * How many pixels should be lit?
     */
    fun solvePart1(): Int =
        screen.fold(0) { carry, next -> carry + next.count { it } }

    /**
     * What code is the screen trying to display?
     */
    fun solvePart2(): String =
        screen.map { it.map { if (it) "#" else " " }.joinToString("") }.joinToString("\n")

    // This is all side-effects, ew.
    fun interpretCommand(command: String) {
        val (a, b) = getTheDigits(command)
        when {
            command.startsWith("rect") -> {
                (0 until b).forEach { x ->
                    (0 until a).forEach { y ->
                        screen[x][y] = true
                    }
                }
            }
            command.startsWith("rotate row") ->
                screen[a] = rotate(screen[a], b)
            command.startsWith("rotate column") -> {
                val rotated = rotate(colToRow(a), b)
                (0 until height).forEach { screen[it][a] = rotated[it] }
            }
        }
    }

    private fun colToRow(col: Int): Array<Boolean> =
        (0 until height).map { screen[it][col] }.toTypedArray()

    private fun rotate(row: Array<Boolean>, by: Int): Array<Boolean> =
        (row.takeLast(by) + row.dropLast(by)).toTypedArray()

    fun getTheDigits(line: String): Pair<Int, Int> =
        digits.matchEntire(line)?.destructured?.let {
            Pair(it.component1().toInt(), it.component2().toInt())
        } ?: Pair(0, 0)
}
