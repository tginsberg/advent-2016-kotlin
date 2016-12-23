/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016


/**
 * Advent of Code - Day 23: December 23, 2016
 *
 * From http://adventofcode.com/2016/day/23
 *
 */
class Day23(input: List<String>) {
    companion object {
        val COPY = Regex("""^cpy (\S+) (\S+)$""")
        val INC = Regex("""^inc ([abcd])$""")
        val DEC = Regex("""^dec ([abcd])$""")
        val JUMP = Regex("""^jnz (\S+) (\S+)$""")
        val TOGGLE = Regex("""^tgl ([abcd])$""")
    }

    val instructions: List<Instruction> = parseInstructions(input)

    fun solvePart1(): Int =
        solve(mapOf('a' to 7))

    fun solvePart2(): Int =
        solve(mapOf('a' to 12))

    private fun solve(registers: Map<Char, Int>): Int {
        var state = State(instructions, registers, 0)
        while (!state.isDone()) {
            state = state.op()
        }
        return state.registers['a'] ?: 0
    }

    private fun parseInstructions(instructions: List<String>): List<Instruction> {
        return instructions.map {
            when {
                COPY.matches(it) -> {
                    val (x, y) = COPY.matchEntire(it)?.destructured!!
                    Instruction.Copy(x, y)
                }
                INC.matches(it) -> {
                    val (x) = INC.matchEntire(it)?.destructured!!
                    Instruction.Inc(x)
                }
                DEC.matches(it) -> {
                    val (x) = DEC.matchEntire(it)?.destructured!!
                    Instruction.Dec(x)
                }
                JUMP.matches(it) -> {
                    val (x, y) = JUMP.matchEntire(it)?.destructured!!
                    Instruction.Jump(x, y)
                }
                TOGGLE.matches(it) -> {
                    val (x) = TOGGLE.matchEntire(it)?.destructured!!
                    Instruction.Toggle(x)
                }
                else -> Instruction.NoOp
            }
        }
    }

    data class State(val instructions: List<Instruction>, val registers: Map<Char, Int>, val pc: Int = 0) {
        fun pcDelta(v: Int) =
            this.copy(pc = pc + v)

        fun isDone(): Boolean =
            instructions.size <= pc

        fun op(): State {
            val nextOp: Instruction = instructions[pc]
            return if (nextOp.isValid()) nextOp.execute(this)
            else this.pcDelta(1)
        }
    }

    sealed class Instruction() {
        fun toIntOrNull(x: String): Int? =
            try {
                x.toInt()
            } catch (e: NumberFormatException) {
                null
            }

        fun toRegisterOrNull(r: String): Char? =
            if (r[0] in setOf('a', 'b', 'c', 'd')) r[0]
            else null

        open fun isValid(): Boolean = true

        abstract fun execute(state: State): State
        abstract fun toggle(togglingInstruction: Instruction): Instruction

        class Copy(val x: String, val y: String) : Instruction() {
            override fun isValid(): Boolean =
                (toRegisterOrNull(x) != null || toIntOrNull(x) != null) && toRegisterOrNull(y) != null

            override fun execute(state: State): State {
                val cpyVal = toRegisterOrNull(x)?.let { it -> state.registers[it] } ?: toIntOrNull(x)!!
                val toReg = toRegisterOrNull(y)!!
                return state.copy(registers = state.registers.plus(toReg to cpyVal)).pcDelta(1)
            }

            override fun toggle(togglingInstruction: Instruction): Instruction =
                Jump(x, y)
        }

        class Inc(val x: String) : Instruction() {
            override fun isValid(): Boolean =
                toRegisterOrNull(x) != null

            override fun execute(state: State): State {
                val toReg = toRegisterOrNull(x)!!
                return state.copy(registers = state.registers.plus(toReg to state.registers[toReg]!! + 1)).pcDelta(1)
            }

            override fun toggle(togglingInstruction: Instruction): Instruction =
                Dec(x)
        }

        class Dec(val x: String) : Instruction() {
            override fun isValid(): Boolean =
                toRegisterOrNull(x) != null

            override fun execute(state: State): State {
                val toReg = toRegisterOrNull(x)!!
                return state.copy(registers = state.registers.plus(toReg to state.registers[toReg]!! - 1)).pcDelta(1)
            }

            override fun toggle(togglingInstruction: Instruction): Instruction =
                Inc(x)
        }

        class Jump(val x: String, val y: String) : Instruction() {
            override fun execute(state: State): State {
                val cmpVal = toRegisterOrNull(x)?.let { it -> state.registers[it] } ?: toIntOrNull(x)!!
                val jmpVal = toRegisterOrNull(y)?.let { it -> state.registers[it] } ?: toIntOrNull(y)!!
                return if (cmpVal == 0) state.pcDelta(1)
                else state.pcDelta(jmpVal)
            }

            override fun toggle(togglingInstruction: Instruction): Instruction =
                Copy(x, y)
        }

        class Toggle(val x: String) : Instruction() {
            private var skippedSelfToggle = false

            override fun execute(state: State): State {
                val away = toRegisterOrNull(x)?.let { it -> state.registers[it] } ?: toIntOrNull(x)!!
                val at = state.pc + away
                val newInst = state.instructions.toMutableList()
                if (at < newInst.size) {
                    newInst[at] = newInst[at].toggle(this)
                    return state.copy(newInst).pcDelta(1)
                }
                return state.pcDelta(1)
            }

            override fun toggle(togglingInstruction: Instruction): Instruction =
                if (togglingInstruction == this) {
                    if (skippedSelfToggle) {
                        skippedSelfToggle = true
                        this
                    } else {
                        Inc(x)
                    }
                } else {
                    Inc(x)
                }
        }

        object NoOp : Instruction() {
            override fun execute(state: State): State =
                state.pcDelta(1)

            override fun toggle(togglingInstruction: Instruction): Instruction =
                NoOp
        }
    }
}
