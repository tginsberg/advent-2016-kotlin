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
 * You are faced with a security door designed by Easter Bunny engineers that seem to have acquired
 * most of their security knowledge by watching hacking movies.
 *
 * The eight-character password for the door is generated one character at a time by finding the MD5
 * hash of some Door ID (your puzzle input) and an increasing integer index (starting with 0).
 *
 * A hash indicates the next character in the password if its hexadecimal representation starts
 * with five zeroes. If it does, the sixth character in the hash is the next character of the password.
 *
 * [ See test for examples ]
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
        generateSequence(
            Pair(1, md5("${doorId}1")),
            { Pair(it.first+1, md5("$doorId${it.first}")) }
        )
            .map { it.second }
            .filter { it.startsWith("00000") }

}
