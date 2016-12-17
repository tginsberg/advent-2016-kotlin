/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import java.util.ArrayDeque


/**
 * Advent of Code - Day 13: December 13, 2016
 *
 * From http://adventofcode.com/2016/day/13
 *
 */
class Day13(favorite: Int, val goalX: Int, val goalY: Int, val depth: Int = 50) {

    private val start = Node(1, 1, 0, favorite)

    fun solvePart1(): Int = searchGrid()

    fun solvePart2(): Int = countStepsToDepth()

    fun searchGrid(): Int {
        var found: Set<Pair<Int, Int>> = emptySet()
        val queue = ArrayDeque<Node>().apply {
            add(start)
        }
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            found += Pair(current.x, current.y)
            if (current.x == goalX && current.y == goalY) {
                return current.distance
            } else {
                queue.addAll(
                    current
                        .neighbors()
                        .filterNot { found.contains(Pair(it.x, it.y)) }
                )
            }
        }
        return -1
    }

    fun countStepsToDepth(): Int {
        var found: Set<Pair<Int, Int>> = emptySet()
        val queue = ArrayDeque<Node>().apply {
            add(start)
        }
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            found += Pair(current.x, current.y)
            queue.addAll(
                current
                    .neighbors()
                    .filter { it.distance <= depth }
                    .filterNot { found.contains(Pair(it.x, it.y)) }
            )
        }
        return found.size
    }

    data class Node(val x: Int, val y: Int, val distance: Int = 0, private val favorite: Int = 10) {
        fun neighbors(): List<Node> =
            listOf(
                this.copy(x = x.inc(), distance = distance.inc()),
                this.copy(x = x.dec(), distance = distance.inc()),
                this.copy(y = y.inc(), distance = distance.inc()),
                this.copy(y = y.dec(), distance = distance.inc()))
                .filter { it.inBounds() }
                .filter { it.isOpen() }

        fun inBounds(): Boolean =
            x >= 0 && y >= 0

        fun isOpen(): Boolean =
            Integer.bitCount(((x * x) + (3 * x) + (2 * x * y) + y + (y * y)) + favorite) % 2 == 0
    }
}
