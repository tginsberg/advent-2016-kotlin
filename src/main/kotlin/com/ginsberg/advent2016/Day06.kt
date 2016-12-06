/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 6: December 6, 2016
 *
 * From http://adventofcode.com/2016/day/6
 *
 * Something is jamming your communications with Santa. Fortunately, your signal is only
 * partially jammed, and protocol in situations like this is to switch to a simple repetition
 * code to get the message through.
 *
 * In this model, the same message is sent repeatedly. You've recorded the repeating message
 * signal (your puzzle input), but the data seems quite corrupted - almost too badly to recover.
 * Almost.
 *
 * All you need to do is figure out which character is most frequent for each position. For example,
 * suppose you had recorded the following messages:
 *
 * [See Unit Test]
 *
 */
class Day06(val input: List<String>) {

    /**
     * Given the recording in your puzzle input, what is the error-corrected
     * version of the message being sent?
     */
    fun solvePart1(): String =
        (0 until input[0].length)
            .map { i -> input.map { it[i] } }
            .map { it.groupBy { it }.maxBy { it.value.size }?.key ?: ' ' }
            .joinToString(separator = "")

    /**
     * Given the recording in your puzzle input and this new decoding methodology,
     * what is the original message that Santa is trying to send?
     */
    fun solvePart2(): String =
        (0 until input[0].length)
            .map { i -> input.map { it[i] } }
            .map { it.groupBy { it }.minBy { it.value.size }?.key ?: ' ' }
            .joinToString(separator = "")

}
