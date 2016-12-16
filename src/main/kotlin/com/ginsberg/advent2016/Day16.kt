/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 16: December 16, 2016
 *
 * From http://adventofcode.com/2016/day/16
 *
 */
class Day16(val input: String) {

    fun solve(length: Int): String =
        checksum(dataStream(input)
            .dropWhile { it.length < length }
            .first()
            .substring(0, length))

    tailrec fun checksum(s: String): String {
        val check = (0..s.length - 1 step 2)
            .map { s.substring(it, it + 2) }
            .map { if (it[0] == it[1]) "1" else "0" }
            .joinToString("")
        return if (check.length % 2 == 1) check else checksum(check)
    }

    fun dataStream(initial: String): Sequence<String> {
        fun next(s: String): String =
            s + '0' + s.reversed()
                .map{ when(it) {
                    '0' -> '1'
                    '1' -> '0'
                    else -> it
                }}.joinToString("")
        return generateSequence(
            next(initial),
            { next(it) }
        )
    }
}
