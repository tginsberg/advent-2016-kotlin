/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.toHex

/**
 * Advent of Code - Day 2: December 2, 2016
 *
 * From http://adventofcode.com/2016/day/2
 *
 */
class Day02(val instructions: List<String>, val startingPoint: Int = 5) {

    /**
     * Keypad like this:
     *  1 2 3
     *  4 5 6
     *  7 8 9
     */
    fun solvePart1(): String {
        return solve { from: Int, via: Char ->
            from + when (via) {
                'L' -> if (setOf(1, 4, 7).contains(from)) 0 else -1
                'R' -> if (setOf(3, 6, 9).contains(from)) 0 else 1
                'U' -> if (setOf(1, 2, 3).contains(from)) 0 else -3
                'D' -> if (setOf(7, 8, 9).contains(from)) 0 else 3
                else -> 0
            }
        }
    }

    /**
     * Keypad like this:
     *      1
     *    2 3 4
     *  5 6 7 8 9
     *    A B C
     *      D
     */
    fun solvePart2(): String {
        return solve { from: Int, via: Char ->
            from + when (via) {
                'L' -> if (setOf(1, 2, 5, 10, 13).contains(from)) 0
                       else -1
                'R' -> if (setOf(1, 4, 9, 12, 13).contains(from)) 0
                       else 1
                'U' -> if (setOf(1, 2, 4, 5, 9).contains(from)) 0
                       else if (setOf(3, 13).contains(from)) -2
                       else -4
                'D' -> if (setOf(5, 9, 10, 12, 13).contains(from)) 0
                       else if (setOf(1, 11).contains(from)) 2
                       else 4
                else -> 0
            }
        }
    }

    /**
     * Execute the inputs one by one, given a keypad mapping function
     */
    private fun solve(keymapFunction: (a: Int, b: Char) -> Int): String {
        tailrec fun inner(digits: List<Int>, lines: List<String>): String {
            if(lines.isEmpty()) {
                return digits.map(Int::toHex).joinToString(separator = "")
            } else {
                val from = digits.lastOrNull() ?: startingPoint
                return inner(digits + stringToDigit(from, lines[0], keymapFunction), lines.drop(1))
            }
        }
        return inner(emptyList(), instructions)
    }

    private fun stringToDigit(start: Int, input: String, op: (a: Int, b: Char) -> Int): Int =
            input.fold(start){ carry, next ->  op(carry, next) }

}
