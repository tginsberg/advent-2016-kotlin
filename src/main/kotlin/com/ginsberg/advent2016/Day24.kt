/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.asDigit
import java.util.ArrayDeque


/**
 * Advent of Code - Day 24: December 24, 2016
 *
 * From http://adventofcode.com/2016/day/24
 *
 */
class Day24(val input: List<String>) {

    val board: List<List<Spot>> = parseInput()
    val numberedSpots: Map<Int, Spot> = findNumberedSpots(board)
    val distances: Map<Pair<Int, Int>, Int> = mapAllDistances(numberedSpots)

    private fun mapAllDistances(numberedSpots: Map<Int, Spot>): Map<Pair<Int, Int>, Int> {
        val oneWay = numberedSpots.flatMap { l ->
            numberedSpots.filter { l.key < it.key }.map { r -> Pair(l.key, r.key) to search(l.value, r.value) }
        }.toMap()
        return oneWay.plus(oneWay.map { Pair(it.key.second, it.key.first) to it.value })
    }

    fun solvePart1(): Int =
        findMinPath(numberedSpots.keys)

    fun solvePart2(): Int =
        findMinPath(numberedSpots.keys, true)

    fun search(from: Spot, to: Spot): Int {
        val visited = mutableMapOf(from to 0)
        val queue = ArrayDeque<Spot>().apply { add(from) }
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            val dist = visited[current]!!
            if (current == to) {
                return dist
            }
            val neighbors = listOf(
                board[current.x][current.y - 1],
                board[current.x][current.y + 1],
                board[current.x - 1][current.y],
                board[current.x + 1][current.y])
                .filter { it.isValid() && !it.isWall() }
                .filterNot { it in visited }
            visited.putAll(neighbors.map { it to dist + 1 })
            queue.addAll(neighbors)
        }
        return 0
    }

    private fun parseInput(): List<List<Spot>> =
        (0..input.size - 1).map { x ->
            (0..input[x].length - 1).map { y ->
                Spot(x, y, input[x][y])
            }
        }

    private fun findNumberedSpots(board: List<List<Spot>>): Map<Int, Spot> =
        board.flatMap { it.filter { it.getGoalNumber() != null } }.associateBy { it.getGoalNumber()!! }

    private fun findMinPath(all: Set<Int>, loopBack: Boolean = false): Int {
        fun inner(unvisited: Set<Int>, at: Int = 0, acc: Int = 0): Int =
            if (unvisited.isEmpty()) acc + (if (loopBack) distances[Pair(at, 0)]!! else 0)
            else all.filter { it in unvisited }.map { it -> inner(unvisited.minus(it), it, acc + distances[Pair(at, it)]!!) }.min()!!
        return inner(all.minus(0), 0, 0)
    }

    data class Spot(val x: Int, val y: Int, val contains: Char) {
        fun isValid(): Boolean = x >= 0 && y >= 0
        fun isWall(): Boolean = contains == '#'
        fun getGoalNumber(): Int? = try {
            contains.asDigit()
        } catch(e: Exception) {
            null
        }
    }
}
