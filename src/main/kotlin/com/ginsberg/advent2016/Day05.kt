/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.asDigit
import com.ginsberg.advent2016.utils.toHex
import java.security.MessageDigest


/**
 * Advent of Code - Day 5: December 5, 2016
 *
 * From http://adventofcode.com/2016/day/5
 *
 */
class Day05(private val doorId: String) {

    private val md5Digester = MessageDigest.getInstance("MD5")

    /**
     * Given the actual Door ID, what is the password?
     */
    fun solvePart1(): String =
        generateHashes()
            .map { it[5] }
            .take(8)
            .joinToString(separator = "")

    /**
     * Given the actual Door ID and this new method, what is the password?
     */
    fun solvePart2(): String =
        generateHashes()
            .map { Pair(it[5], it[6]) }
            .filter { it.first.isDigit() && it.first.asDigit() < 8 }
            .distinctBy { it.first }
            .take(8)
            .sortedBy { it.first }
            .map { it.second }
            .joinToString(separator = "")

    private fun md5(text: String): String =
        md5Digester.digest(text.toByteArray()).toHex()

    // Common sequence logic (increment, hash, filter)
    private fun generateHashes(): Sequence<String> =
        generateSequence(0, Int::inc)
            .map { md5("$doorId$it")}
            .filter { it.startsWith("00000") }
}
