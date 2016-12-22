/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 21: December 21, 2016
 *
 * From http://adventofcode.com/2016/day/21
 *
 */
class Day21(val instructions: List<String>) {
    companion object {
        private val SWAP_POSITION = Regex("""^swap position (\d+) with position (\d+)$""")
        private val SWAP_LETTERS = Regex("""^swap letter (\S) with letter (\S)$""")
        private val ROTATE_SHIFT = Regex("""^rotate (left|right) (\d+) step(s?)$""")
        private val ROTATE_LETTER = Regex("""^rotate based on position of letter (\S)$""")
        private val REVERSE = Regex("""^reverse positions (\d+) through (\d+)$""")
        private val MOVE = Regex("""^move position (\d+) to position (\d+)$""")
        private val RIGHT_SHIFT = listOf(1,2,3,4,6,7,8,9)
        private val LEFT_SHIFT = listOf(9,1,6,2,7,3,8,4)
    }

    fun solvePart1(word: String): String =
        instructions.fold(word){ carry, next -> mutate(carry, next, true)}

    fun solvePart2(word: String): String =
        instructions.reversed().fold(word){ carry, next -> mutate(carry, next, false)}

    fun mutate(input: String, instruction: String, forward: Boolean): String =
        when {
            SWAP_POSITION.matches(instruction) -> {
                val (x, y) = SWAP_POSITION.matchEntire(instruction)!!.destructured
                swapPosition(input, x.toInt(), y.toInt())
            }
            SWAP_LETTERS.matches(instruction) -> {
                val (x, y) = SWAP_LETTERS.matchEntire(instruction)!!.destructured
                swapLetters(input, x[0], y[0])
            }
            ROTATE_SHIFT.matches(instruction) -> {
                val (dir, x) = ROTATE_SHIFT.matchEntire(instruction)!!.destructured
                if(forward) rotateShift(input, dir, x.toInt())
                else rotateShift(input, if(dir == "left") "right" else "left", x.toInt())
            }
            ROTATE_LETTER.matches(instruction) -> {
                val (x) = ROTATE_LETTER.matchEntire(instruction)!!.destructured
                rotateLetter(input, x[0], forward)
            }
            REVERSE.matches(instruction) -> {
                val (x, y) = REVERSE.matchEntire(instruction)!!.destructured
                reverse(input, x.toInt(), y.toInt())
            }
            MOVE.matches(instruction) -> {
                val (x, y) = MOVE.matchEntire(instruction)!!.destructured
                if(forward) move(input, x.toInt(), y.toInt())
                else move(input, y.toInt(), x.toInt())
            }
            else -> input
        }

    fun swapPosition(input: String, x: Int, y: Int): String {
        val arr = input.toCharArray()
        val tmp = input[x]
        arr[x] = arr[y]
        arr[y] = tmp
        return arr.joinToString("")
    }

    fun swapLetters(input: String, x: Char, y: Char): String =
        input.map { if(it == x) y else if(it == y) x else it }.joinToString("")

    fun rotateShift(input: String, direction: String, steps: Int): String {
        val sm = steps % input.length
        return if (direction == "left") input.drop(sm) + input.take(sm)
               else input.drop(input.length - sm).take(sm) + input.take(input.length - sm)
    }

    fun rotateLetter(input: String, ch: Char, forward: Boolean): String {
        val idx = input.indexOf(ch)
        return if(forward) rotateShift(input, "right", RIGHT_SHIFT[idx])
        else rotateShift(input, "left", LEFT_SHIFT[idx])
    }

    fun reverse(input: String, x: Int, y: Int): String =
        input.substring(0, x) + input.drop(x).take(y-x+1).reversed() + input.drop(y+1)

    fun move(input: String, x: Int, y: Int): String {
        val ch = input[x]
        val unX = input.substring(0, x) + input.substring(x+1)
        return unX.substring(0, y) + ch + unX.substring(y)
    }

}
