/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.toHex
import java.security.MessageDigest


/**
 * Advent of Code - Day 14: December 14, 2016
 *
 * From http://adventofcode.com/2016/day/14
 *
 */
class Day14(val salt: String, val find: Int = 64, val nestingFactor: Int = 2016) {
    companion object {
        val md5Digester: MessageDigest = MessageDigest.getInstance("MD5")
        val threeDigits = Regex(""".*?([0-9a-f])\1\1.*""")
        val fiveDigits = Regex(""".*([0-9a-f])\1\1\1\1.*""")
    }

    private var hashCache = mapOf<Int,String>()

    fun solvePart1(): Int =
        solve(false)

    fun solvePart2(): Int =
        solve(true)

    private fun solve(nested: Boolean): Int =
        hashes(nested = nested)
            .map { Triple(it.first, it.second, tripleDigit(it.second)) }
            .filterNot { it.third == null }
            .filter { matchesFutureFive(it.third!!, it.first, nested) }
            .drop(find-1)
            .first()
            .first

    private fun matchesFutureFive(match: Char, iteration: Int, nested: Boolean): Boolean =
        hashes(iteration+1, nested).take(1000).any { isMatchingFive(match, it.second)}

    private fun isMatchingFive(match: Char, hash: String) =
        fiveDigits.matchEntire(hash)?.destructured?.component1()?.get(0) == match

    private fun tripleDigit(hash: String): Char? =
        threeDigits.matchEntire(hash)?.destructured?.component1()?.get(0)

    private fun hashes(start: Int = 1, nested: Boolean): Sequence<Pair<Int, String>> =
        generateSequence(
            Pair(start, if(nested) md5Nested("$salt$start") else md5("$salt$start")),
            { generateOrFindHash(it.first+1, nested) }
        )

    fun generateOrFindHash(id: Int, nested: Boolean): Pair<Int, String> {
        if(!hashCache.containsKey(id)) {
            hashCache = hashCache.filterNot { it.key+1000 < id }
            hashCache += (id to if(nested) md5Nested("$salt$id") else md5("$salt$id"))
        }
        return Pair(id, hashCache[id]!!)
    }

    private fun md5Nested(text: String): String =
        (1..nestingFactor).fold(md5(text)){ carry, next -> md5(carry) }

    private fun md5(text: String): String =
        md5Digester.digest(text.toByteArray()).toHex()

}
