/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 7: December 7, 2016
 *
 * From http://adventofcode.com/2016/day/7
 *
 * While snooping around the local network of EBHQ, you compile a list of IP addresses
 * (they're IPv7, of course; IPv6 is much too limited). You'd like to figure out which IPs
 * support TLS (transport-layer snooping).
 *
 * An IP supports TLS if it has an Autonomous Bridge Bypass Annotation, or ABBA. An ABBA is any
 * four-character sequence which consists of a pair of two different characters followed by the
 * reverse of that pair, such as xyyx or abba. However, the IP also must not have an ABBA within
 * any hypernet sequences, which are contained by square brackets.
 *
 * [See Unit Test]
 *
 */
class Day07(private val input: List<String>) {

    /**
     * How many IPs in your puzzle input support TLS?
     */
    fun solvePart1(): Int =
        input.count { supportsTls(it) }

    /**
     * How many IPs in your puzzle input support SSL?
     */
    fun solvePart2(): Int =
        input.count { supportsSsl(it) }


    private fun supportsTls(address: String): Boolean {
        val parts = toDelimitedParts(address)
        return parts.any { isAbba(it) } && parts.none { isHypernet(it) }
    }

    // I'm sure there is a better way to do this with Regex.
    private fun isAbba(part: String): Boolean =
        (0 until part.length-3)
            .filter { i -> part[i] == part[i+3] &&
                           part[i+1] == part[i+2] &&
                           part[i] != part[i+1] }
            .isNotEmpty()

    private fun isHypernet(part: String): Boolean =
        part.startsWith("[") && part.endsWith("]") && isAbba(part)

    private fun toDelimitedParts(address: String): List<String> =
        address.split(Regex("(?=\\[)|(?<=\\])"))

    private fun supportsSsl(address: String): Boolean {
        val parts = toDelimitedParts(address)
        val abas = parts
            .filter { !it.startsWith("[") }
            .fold(emptySet<String>()) { carry, next -> gatherAbas(next) + carry }
        val babs = parts
            .filter { it.startsWith("[") }
            .fold(emptySet<String>()) { carry, next -> gatherBabs(next) + carry }
        return abas.filter { babs.contains(it) }.isNotEmpty()
    }

    // I'm sure there is a better way to do this with Regex and capture groups.
    private fun gatherAbas(input: String): Set<String> =
        (0 until input.length-2)
            .map { i -> input.substring(i, i+3) }
            .filter { it[0] == it[2] && it[0] != it[1] }
            .toSet()

    private fun gatherBabs(input:String): Set<String> =
        gatherAbas(input).map { babToAba(it) }.toSet()

    private fun babToAba(input: String): String =
        listOf(input[1], input[0], input[1]).joinToString(separator = "")

}
