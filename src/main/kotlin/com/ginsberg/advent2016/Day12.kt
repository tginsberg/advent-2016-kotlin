/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 12: December 12, 2016
 *
 * From http://adventofcode.com/2016/day/12
 *
 */
class Day12(input: List<String>) {
    companion object {
        val copyInt: Regex = Regex("""^cpy (-?\d+) ([abcd])$""")
        val copyReg: Regex = Regex("""^cpy ([abcd]) ([abcd])$""")
        val inc: Regex = Regex("""^inc ([abcd])$""")
        val dec: Regex = Regex("""^dec ([abcd])$""")
        val jumpInt= Regex("""^jnz (-?\d+) (-?\d+)$""")
        val jumpReg = Regex("""^jnz ([abcd]) (-?\d+)$""")
    }

    val instructions: List<Instruction> = parseInstructions(input)

    fun solvePart1(): Int =
        solve(mapOf('a' to 0, 'b' to 0, 'c' to 0, 'd' to 0))

    fun solvePart2(): Int =
        solve(mapOf('a' to 0, 'b' to 0, 'c' to 1, 'd' to 0))

    private fun solve(registers: Map<Char, Int>): Int {
        var state = State(registers, 0)
        while(state.pc < instructions.size) {
            state = instructions[state.pc].execute(state)
        }
        return state.registers['a'] ?: 0
    }


    private fun parseInstructions(instructions: List<String>): List<Instruction> {
        return instructions.map {
            when {
                copyInt.matches(it) -> {
                    val (x, y) = copyInt.matchEntire(it)?.destructured!!
                    Instruction.CopyInt(y[0], x.toInt())
                }
                copyReg.matches(it) -> {
                    val (x, y) = copyReg.matchEntire(it)?.destructured!!
                    Instruction.CopyRegister(x[0], y[0])
                }
                inc.matches(it) -> {
                    val (x) = inc.matchEntire(it)?.destructured!!
                    Instruction.Inc(x[0])
                }
                dec.matches(it) -> {
                    val (x) = dec.matchEntire(it)?.destructured!!
                    Instruction.Dec(x[0])
                }
                jumpInt.matches(it) -> {
                    val (x, y) = jumpInt.matchEntire(it)?.destructured!!
                    Instruction.JumpInt(x.toInt(), y.toInt())
                }
                jumpReg.matches(it) -> {
                    val (x, y) = jumpReg.matchEntire(it)?.destructured!!
                    Instruction.JumpReg(x[0], y.toInt())
                }
                else -> Instruction.NoOp
            }
        }
    }

    data class State(val registers: Map<Char,Int>, val pc: Int = 0) {
        fun pcDelta(v: Int) = this.copy(pc = pc + v)
    }

    sealed class Instruction() {
        abstract fun execute(state: State): State
        class CopyInt(val register: Char, val amount: Int) : Instruction() {
            override fun execute(state: State): State =
                state.copy(state.registers.plus(register to amount)).pcDelta(1)
        }

        class CopyRegister(val fromReg: Char, val toReg: Char) : Instruction() {
            override fun execute(state: State): State =
                state.copy(state.registers.plus(toReg to state.registers[fromReg]!!)).pcDelta(1)
        }

        class Inc(val register: Char) : Instruction() {
            override fun execute(state: State): State =
                state.copy(state.registers.plus(register to state.registers[register]!!+1)).pcDelta(1)
        }

        class Dec(val register: Char) : Instruction() {
            override fun execute(state: State): State =
                state.copy(state.registers.plus(register to state.registers[register]!!-1)).pcDelta(1)
        }

        class JumpInt(val compare: Int, val jump: Int) : Instruction() {
            override fun execute(state: State): State =
                if(compare == 0) state.pcDelta(1)
                else state.pcDelta(jump)
        }

        class JumpReg(val register: Char, val jump: Int) : Instruction() {
            override fun execute(state: State): State =
                if(state.registers[register] == 0) state.pcDelta(1)
                else state.pcDelta(jump)
        }

        object NoOp : Instruction() {
            override fun execute(state: State): State =
                state.pcDelta(1)
        }
    }
}
