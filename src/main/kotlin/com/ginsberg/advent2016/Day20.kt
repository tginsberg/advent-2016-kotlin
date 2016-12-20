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
    val ipRanges = optimizeRanges(parseInput())

    fun solvePart1(): Long =
        (0 until ipRanges.size-1 step  2)
            .filter { !ipRanges[it+1].contains(ipRanges[it].last + 1) }
            .map { ipRanges[it].last + 1}
            .first()

    fun solvePart2(upperBound: Long = 4294967295): Long =
        upperBound.inc() - ipRanges.map{ (it.last - it.first).inc() }.sum()

    private fun parseInput(): List<LongRange> =
        input
            .map { it.split("-") }
            .map { LongRange(it[0].toLong(), it[1].toLong()) }
            .sortedBy { it.first }

    private fun optimizeRanges(ranges: List<LongRange>): List<LongRange> {
        tailrec fun inner(done: List<LongRange>, working: LongRange, target: LongRange, rest: List<LongRange>): List<LongRange> =
            when {
                rest.isEmpty() && working.combinesWith(target) -> done.plusElement(working.plus(target))
                rest.isEmpty() -> done.plusElement(working).plusElement(target)
                working.combinesWith(target) -> inner(done, working.plus(target), rest.first(), rest.drop(1))
                else -> inner(done.plusElement(working), target, rest.first(), rest.drop(1))
            }

        return inner(emptyList(), ranges.first(), ranges.drop(1).first(), ranges.drop(2))
    }

    private fun LongRange.plus(other: LongRange): LongRange =
        LongRange(Math.min(this.first, other.first), Math.max(this.last, other.last))

    private fun LongRange.combinesWith(other: LongRange): Boolean =
        other.first in this || this.last+1 in other

}
