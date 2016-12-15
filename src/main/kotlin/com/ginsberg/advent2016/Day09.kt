/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 9: December 9, 2016
 *
 * From http://adventofcode.com/2016/day/9
 *
 */
class Day09(private val input: String) {

    private val digits = Regex("""^\((\d+)x(\d+)\).*""")

    /**
     * What is the decompressed length of the file (your puzzle input)?
     */
    fun solvePart1(): Long =
        decodeV1Length(input.replace("""\s+""", ""))

    /**
     * What is the decompressed length of the file using this improved format?
     */
    fun solvePart2(): Long =
        decodeV2Length(input.replace("""\s+""", ""))

    private tailrec fun decodeV1Length(rest: String, carry: Long = 0): Long =
        when {
            rest.isEmpty() -> carry
            !rest.startsWith("(") -> decodeV1Length(rest.substringAt("("), carry + rest.substringBefore("(").length)
            else -> {
                val digits = parseDigitsFromHead(rest)
                val headless = rest.substringAfter(")")
                decodeV1Length(
                    headless.substring(digits.first),
                    carry + (digits.first * digits.second)
                )
            }
        }

    private tailrec fun decodeV2Length(rest: String, carry: Long = 0L): Long =
        when {
            rest.isEmpty() -> carry
            !rest.startsWith("(") -> decodeV2Length(rest.substringAt("("), carry + rest.substringBefore("(").length)
            else -> {
                val digits = parseDigitsFromHead(rest)
                val headless = rest.substringAfter(")")
                decodeV2Length(
                    headless.substring(digits.first),
                    carry + ((digits.second) * decodeV2Length(headless.substring(0, digits.first)))
                )
            }
        }

    fun String.substringAt(str: String): String =
        if (this.contains(str)) str + this.substringAfter(str)
        else ""

    fun parseDigitsFromHead(line: String): Pair<Int, Int> =
        digits.matchEntire(line)?.destructured?.let {
            Pair(it.component1().toInt(), it.component2().toInt())
        } ?: Pair(0, 0)
}
