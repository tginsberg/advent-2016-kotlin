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

    data class Node(val loc: Pair<Int, Int>, val used: Int, val free: Int)

    val nodes: List<Node> = dfRows.map { parseNode(it) }.filterNotNull().sortedByDescending { it.free }

    fun solvePart1(): Int =
        pairedBySize().values.map { it.size }.sum()

    fun solvePart2(): Int = TODO()

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
            return Node(Pair(x.toInt(), y.toInt()), used.toInt(), free.toInt())
        }
        return null
    }

}
