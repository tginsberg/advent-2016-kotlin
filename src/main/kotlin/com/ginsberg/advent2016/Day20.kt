/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 20: December 20, 2016
 *
 * From http://adventofcode.com/2016/day/20
 *
 */
class Day20(val input: List<String>) {
    val ipRanges = parseInput()

    fun solvePart1(): Long =
        ipRanges.first().last.inc()

    fun solvePart2(upperBound: Long = 4294967295): Long =
        upperBound.inc() - ipRanges.map { (it.last - it.first).inc() }.sum()

    private fun parseInput(): List<LongRange> =
        optimize(input
            .map { it.split("-") }
            .map { LongRange(it[0].toLong(), it[1].toLong()) }
            .sortedBy { it.first }
        )

    private fun optimize(ranges: List<LongRange>): List<LongRange> =
        ranges.drop(1).fold(ranges.take(1)) { carry, next ->
            if (carry.last().combinesWith(next)) carry.dropLast(1).plusElement(carry.last().plus(next))
            else carry.plusElement(next)
        }

    private fun LongRange.plus(other: LongRange): LongRange =
        LongRange(Math.min(this.first, other.first), Math.max(this.last, other.last))

    private fun LongRange.combinesWith(other: LongRange): Boolean =
        other.first in this || this.last + 1 in other
}
