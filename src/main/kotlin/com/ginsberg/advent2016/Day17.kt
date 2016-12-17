/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.toHex
import java.security.MessageDigest
import java.util.*


/**
 * Advent of Code - Day 17: December 17, 2016
 *
 * From http://adventofcode.com/2016/day/17
 *
 */
class Day17(val input: String) {
    companion object {
        val MD5: MessageDigest = MessageDigest.getInstance("MD5")
        val GOAL = Pair(3,3)
    }

    private val paths = findPaths()

    // I'd probably solve this with something more efficient, but since  I
    // know part two will just go ask about the last one, I'm just going to
    // calculate the whole thing.
    fun solvePart1(): String = paths.first()

    fun solvePart2(): Int = paths.last().length

    private fun findPaths(): List<String> {
        val start = Room(0, 0, "", input)
        val toBeEvaluated = ArrayDeque<Room>().apply {
            add(start)
        }
        var paths: List<String> = listOf()
        while(toBeEvaluated.isNotEmpty()) {
            val room = toBeEvaluated.poll()
            if(room.x == GOAL.first && room.y == GOAL.second) {
                paths += room.path
            } else {
                toBeEvaluated.addAll(room.neighbors())
            }
        }

        return paths
    }

    data class Room(val x: Int, val y: Int, val path: String, private val key: String) {
        private enum class Direction(val dir: Char, val xD: Int, val yD: Int, val slot: Int) {
            Up('U', 0, -1, 0),
            Down('D', 0, 1, 1),
            Left('L', -1, 0, 2),
            Right('R', 1, 0, 3)
        }

        private val pathHash = md5(key + path).substring(0..3)

        fun neighbors(): List<Room> =
            Direction.values()
                .filter { pathHash[it.slot] in ('b'..'f') }
                .map { Room(x + it.xD, y + it.yD, path + it.dir, key) }
                .filter { it.x in (0..3) && it.y in (0..3) }

        private fun md5(text: String): String =
            MD5.digest(text.toByteArray()).toHex().substring(0..3)
    }
}
