/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 1: December 1, 2016
 *
 * From http://adventofcode.com/2016/day/1
 *
 */
class Day01(instructionText: String) {

    private val instructions = inputToInstructions(instructionText)

    private data class Instruction(val move: Int = 1, val turn: Char? = null)

    private data class State(val x: Int = 0,
                             val y: Int = 0,
                             val facing: Char = 'N') {
        val distance by lazy { Math.abs(x) + Math.abs(y) }
        val coordinates by lazy { Pair(x, y) }
    }

    /**
     * How many blocks away is Easter Bunny HQ?
     */
    fun solvePart1(): Int {
        return instructions
                .fold(State()) { carry, next -> instruct(carry, next) }
                .distance
    }

    /**
     * How many blocks away is the first location you visit twice?
     */
    fun solvePart2(): Int {
        tailrec fun inner(beenTo: Set<Pair<Int,Int>>, path: List<Instruction>, state: State = State()): Int =
            when {
                beenTo.contains(state.coordinates) -> state.distance
                path.isEmpty() -> 0
                else -> {
                    inner(beenTo + state.coordinates, path.drop(1), instruct(state, path[0]))
                }
            }
        return inner(emptySet(), instructionToSteps(instructions))
    }

    private fun instruct(state: State, instruction: Instruction): State {
        val next = if(instruction.turn == null) state else turn(state, instruction.turn)
        return move(next, instruction.move)
    }

    private fun turn(state: State, turn: Char?): State {
        val next = when (state.facing) {
            'N' -> if (turn == 'L') 'W' else 'E'
            'S' -> if (turn == 'L') 'E' else 'W'
            'E' -> if (turn == 'L') 'N' else 'S'
            'W' -> if (turn == 'L') 'S' else 'N'
            else -> state.facing
        }
        return state.copy(facing = next)
    }

    private fun move(state: State, amount: Int = 1): State =
        when(state.facing) {
            'N' -> state.copy(x = state.x + amount)
            'S' -> state.copy(x = state.x - amount)
            'E' -> state.copy(y = state.y + amount)
            'W' -> state.copy(y = state.y - amount)
            else -> state
        }

    private fun inputToInstructions(input: String): List<Instruction> =
            input.split(", ")
                 .map{ Instruction(it.substring(1).toInt(), it[0]) }

    private fun instructionToSteps(instruction: List<Instruction>): List<Instruction> =
            instruction.flatMap {
                listOf(it.copy(move = 1)) + (1 until it.move).map { Instruction() }
            }


}
