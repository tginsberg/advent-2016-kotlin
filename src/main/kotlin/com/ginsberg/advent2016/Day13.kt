/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 13: December 13, 2016
 *
 * From http://adventofcode.com/2016/day/13
 *
 */
class Day13(favorite: Int, goalX: Int, goalY: Int, val depth: Int = 50) {

    private val start = Node(1, 1, favorite)
    private val goal = Node(goalX, goalY, favorite)

    fun solvePart1(): Int = aStarGrid()

    fun solvePart2(): Int = countStepsToDepth()

    fun aStarGrid(to: Node = goal): Int {
        var open: Set<Node> = mutableSetOf(start)
        var closed: Set<Node> = mutableSetOf()
        var cameFrom: Map<Node, Node> = mutableMapOf()
        var gScore: Map<Node, Int> = mutableMapOf(start to 0)
        var fScore: Map<Node, Int> = mutableMapOf(start to start.scoreTo(to))
        while (open.isNotEmpty()) {
            val current: Node = open.minBy { fScore[it] ?: Int.MAX_VALUE }!!
            if (current == to) {
                return gScore[current]!!
            }
            open -= current
            closed += current
            val nextScore = gScore[current]!! + 1
            current.neighbors()
                .filterNot { closed.contains(it) }
                .filterNot { nextScore >= gScore[it] ?: Int.MAX_VALUE }
                .forEach { n ->
                    if (!open.contains(n)) open += n
                    cameFrom = cameFrom.plus(n to current)
                    gScore = gScore.plus(n to nextScore)
                    fScore = fScore.plus(n to nextScore + n.scoreTo(goal))
                }
        }
        return -1
    }

    fun countStepsToDepth(): Int {
        var queue = listOf(start)
        val seen: MutableSet<Node> = mutableSetOf()
        while(queue.isNotEmpty()) {
            val current = queue.first()
            queue = queue.drop(1)
            val distance = aStarGrid(current)
            if(distance <= depth) {
                seen.add(current)
                queue += current.neighbors().filter { it !in seen }
            }
        }
        return seen.count()
    }

    data class Node(val x: Int, val y: Int, private val favorite: Int = 10) {
        fun neighbors(): List<Node> =
            listOf(
                this.copy(x = x.inc()),
                this.copy(x = x.dec()),
                this.copy(y = y.inc()),
                this.copy(y = y.dec()))
                .filter { it.inBounds() }
                .filter { it.isOpen() }

        fun inBounds(): Boolean =
            x >= 0 && y >= 0

        fun isOpen(): Boolean =
            Integer.bitCount(((x * x) + (3 * x) + (2 * x * y) + y + (y * y)) + favorite) % 2 == 0

        fun scoreTo(node: Node) =
            Math.abs(x - node.x) + Math.abs(y + node.y)
    }
}
