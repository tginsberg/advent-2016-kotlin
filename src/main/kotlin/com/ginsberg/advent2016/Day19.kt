/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import java.util.ArrayDeque


/**
 * Advent of Code - Day 19: December 19, 2016
 *
 * From http://adventofcode.com/2016/day/19
 *
 */
class Day19(val input: Int) {

    fun solvePart1(): Int {
        val queue = ArrayDeque<Int>((1..input).toList())
        while (queue.size > 1) {
            queue.add(queue.pop())
            queue.pop()
        }
        return queue.first()
    }

    fun solvePart2(): Int {
        val left = ArrayDeque<Int>((1..input / 2).toList())
        val right = ArrayDeque<Int>(((input / 2) + 1..input).toList())

        while (left.size + right.size > 1) {
            if (left.size > right.size) left.pollLast()
            else right.pollFirst()

            right.addLast(left.pollFirst())
            left.addLast(right.pollFirst())
        }

        return left.firstOrNull() ?: right.first()
    }
}
