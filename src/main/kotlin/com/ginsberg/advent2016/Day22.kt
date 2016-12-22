/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 22: December 22, 2016
 *
 * From http://adventofcode.com/2016/day/22
 *
 */
class Day22(dfRows: List<String>) {
    companion object {
        private val DF_ROW = Regex("""^/dev/grid/node-x(\d+)-y(\d+)\s+(\d+)T\s+(\d+)T\s+(\d+)T\s+(\d+)%$""")
    }

    data class Node(val x: Int, val y: Int, val size: Int, val used: Int, val free: Int)

    val nodes: List<Node> = dfRows.map { parseNode(it) }.filterNotNull().sortedByDescending { it.free }

    fun solvePart1(): Int =
        pairedBySize().values.map { it.size }.sum()

    fun solvePart2(): Int {
        val maxX = nodes.maxBy { it.x }!!.x
        val wall = nodes.filter { it.size > 250 }.minBy { it.x }!!
        val emptyNode = nodes.first { it.used == 0 }
        var result = Math.abs(emptyNode.x - wall.x) + 1 // Empty around wall X.
        result += emptyNode.y // Empty to top
        result += (maxX - wall.x) // Empty over next to goal
        result += (5 * maxX.dec()) + 1 // Goal back to start
        return result
    }

    private fun pairedBySize(): Map<Node, List<Node>> =
        nodes.associateBy(
            { it },
            {
                nodes
                    .filter { me -> me.used > 0 }
                    .filter { me -> me.used < it.free }
                    .filter { me -> me != it }
            }
        ).filterNot { it.value.isEmpty() }

    private fun parseNode(input: String): Node? {
        if (DF_ROW.matches(input)) {
            val (x, y, size, used, free) = DF_ROW.matchEntire(input)!!.destructured
            return Node(x.toInt(), y.toInt(), size.toInt(), used.toInt(), free.toInt())
        }
        return null
    }

}
