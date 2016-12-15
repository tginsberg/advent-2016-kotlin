/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 6: December 6, 2016
 *
 * From http://adventofcode.com/2016/day/6
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
