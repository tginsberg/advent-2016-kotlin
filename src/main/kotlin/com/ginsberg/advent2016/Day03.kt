/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 3: December 3, 2016
 *
 * From http://adventofcode.com/2016/day/3
 *
 * Now that you can think clearly, you move deeper into the labyrinth of hallways
 * and office furniture that makes up this part of Easter Bunny HQ. This must be a
 * graphic design department; the walls are covered in specifications for triangles.
 *
 * Or are they?
 *
 * The design document gives the side lengths of each triangle it describes, but... 5 10 25?
 * Some of these aren't triangles. You can't help but mark the impossible ones.
 *
 * In a valid triangle, the sum of any two sides must be larger than the remaining side.
 *
 * For example, the "triangle" given above is impossible, because 5 + 10 is not larger than 25.
 */
class Day03(rawInput: String) {

    private val inputAsDigits = rawInput.trim().split(Regex("\\s+")).map(String::toInt)

    /**
     * In your puzzle input, how many of the listed triangles are possible?
     */
    fun solvePart1(): Int = countValidTriangles(inputByHorizontal())

    /**
     * In your puzzle input, and instead reading by columns, how many of the
     * listed triangles are possible?
     */
    fun solvePart2(): Int = countValidTriangles(inputByVertical())


    // Takes advantage of descending sort of the triplets so we can
    // just subtract the numbers as a reduction and compare to zero.
    private fun countValidTriangles(sides: List<List<Int>>): Int =
            sides.filter { it.reduce { a, b -> a - b } < 0 }.size

    // Picks out triplets of numbers horizontally
    private fun inputByHorizontal(): List<List<Int>> =
            (0..inputAsDigits.size-3 step 3).map { row ->
                listOf(inputAsDigits[row+0],
                       inputAsDigits[row+1],
                       inputAsDigits[row+2]).sortedDescending()
            }

    // Picks out triplets of numbers vertically. Break the input into groups
    // of nine numbers, and pick out three groups of three.
    private fun inputByVertical(): List<List<Int>>  =
            (0..inputAsDigits.size-9 step 9).flatMap { group ->
                (0..2).map { col ->
                    listOf(inputAsDigits[group+col+0],
                           inputAsDigits[group+col+3],
                           inputAsDigits[group+col+6]).sortedDescending()
                }
            }
}
