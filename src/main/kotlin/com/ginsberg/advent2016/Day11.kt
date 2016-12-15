/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 11: December 11, 2016
 *
 * From http://adventofcode.com/2016/day/11
 *
 */
class Day11(val itemsPerFloor : List<Int>) {

    fun solvePart1(items: List<Int>? = null): Int =
        solve(items ?: itemsPerFloor)

    fun solvePart2(items: List<Int>? = null): Int  =
        solve(items ?: itemsPerFloor)

    private fun solve(items: List<Int>): Int =
        (1..itemsPerFloor.size-1).map { 2 * itemsPerFloor.subList(0, it).sum() - 3 }.sum()
}
