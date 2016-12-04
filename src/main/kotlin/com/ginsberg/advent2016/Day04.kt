/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import kotlin.comparisons.compareBy


/**
 * Advent of Code - Day 4: December 4, 2016
 *
 * From http://adventofcode.com/2016/day/4
 *
 * Finally, you come across an information kiosk with a list of rooms. Of course,
 * the list is encrypted and full of decoy data, but the instructions to decode
 * the list are barely hidden nearby. Better remove the decoy data first.
 *
 * Each room consists of an encrypted name (lowercase letters separated by dashes)
 * followed by a dash, a sector ID, and a checksum in square brackets.
 *
 * A room is real (not a decoy) if the checksum is the five most common letters in
 * the encrypted name, in order, with ties broken by alphabetization.
 *
 * [ See test for examples ]
 *
 */
class Day04(rawInput: String) {

    val rooms = rawInput.split("\n").map(::Room)

    /**
     * What is the sum of the sector IDs of the real rooms?
     */
    fun solvePart1(): Int =
        rooms
            .filter { it.isValid() }
            .map { it.accessCode }
            .sum()

    /**
     * What is the sector ID of the room where North Pole objects are stored?
     */
    fun solvePart2(find: String = "northpole object storage"): Int =
        rooms
            .filter { it.isValid() }
            .filter { it.decrypted == find }
            .first()
            .accessCode


    class Room(raw: String) {
        val name: String = raw.substringBeforeLast('-')
        val accessCode: Int = raw.substringAfterLast('-').substringBefore('[').toInt()
        val checksum: String = raw.substringAfter('[').substringBefore(']')
        val decrypted: String by lazy { decryptName() }

        // Group by frequency, convert to pairs, sort by count desc, letter asc, join first 5.
        fun isValid(): Boolean {
            return name
                .replace("-", "")
                .groupBy { it }
                .mapValues { it.value.size }
                .toList()
                .sortedWith(compareBy({ 0 - it.second }, { it.first }))
                .take(5)
                .map { it.first }
                .joinToString(separator = "") == this.checksum
        }

        private fun decryptName(): String =
            name.map { if (it == '-') ' ' else shiftChar(it) }.joinToString(separator = "")

        private fun shiftChar(c: Char): Char =
            ((((c - 'a') + this.accessCode) % 26) + 'a'.toInt()).toChar()

    }
}
