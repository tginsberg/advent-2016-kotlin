package com.ginsberg.advent2016.assembunny


sealed class AssembunnyInstruction() {
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

    abstract fun execute(state: AssembunnyState): AssembunnyState
    abstract fun toggle(togglingInstruction: AssembunnyInstruction): AssembunnyInstruction

    class Copy(val x: String, val y: String) : AssembunnyInstruction() {
        override fun isValid(): Boolean =
            (toRegisterOrNull(x) != null || toIntOrNull(x) != null) && toRegisterOrNull(y) != null

        override fun execute(state: AssembunnyState): AssembunnyState {
            val cpyVal = toRegisterOrNull(x)?.let { it -> state.registers[it] } ?: toIntOrNull(x)!!
            val toReg = toRegisterOrNull(y)!!
            return state.copy(registers = state.registers.plus(toReg to cpyVal)).pcDelta(1)
        }

        override fun toggle(togglingInstruction: AssembunnyInstruction): AssembunnyInstruction =
            Jump(x, y)
    }

    class Inc(val x: String) : AssembunnyInstruction() {
        override fun isValid(): Boolean =
            toRegisterOrNull(x) != null

        override fun execute(state: AssembunnyState): AssembunnyState {
            val toReg = toRegisterOrNull(x)!!
            return state.copy(registers = state.registers.plus(toReg to state.registers[toReg]!! + 1)).pcDelta(1)
        }

        override fun toggle(togglingInstruction: AssembunnyInstruction): AssembunnyInstruction =
            Dec(x)
    }

    class Dec(val x: String) : AssembunnyInstruction() {
        override fun isValid(): Boolean =
            toRegisterOrNull(x) != null

        override fun execute(state: AssembunnyState): AssembunnyState {
            val toReg = toRegisterOrNull(x)!!
            return state.copy(registers = state.registers.plus(toReg to state.registers[toReg]!! - 1)).pcDelta(1)
        }

        override fun toggle(togglingInstruction: AssembunnyInstruction): AssembunnyInstruction =
            Inc(x)
    }

    class Jump(val x: String, val y: String) : AssembunnyInstruction() {
        override fun execute(state: AssembunnyState): AssembunnyState {
            val cmpVal = toRegisterOrNull(x)?.let { it -> state.registers[it] } ?: toIntOrNull(x)!!
            val jmpVal = toRegisterOrNull(y)?.let { it -> state.registers[it] } ?: toIntOrNull(y)!!
            return if (cmpVal == 0) state.pcDelta(1)
            else state.pcDelta(jmpVal)
        }

        override fun toggle(togglingInstruction: AssembunnyInstruction): AssembunnyInstruction =
            Copy(x, y)
    }

    class Toggle(val x: String) : AssembunnyInstruction() {
        private var skippedSelfToggle = false

        override fun execute(state: AssembunnyState): AssembunnyState {
            val away = toRegisterOrNull(x)?.let { it -> state.registers[it] } ?: toIntOrNull(x)!!
            val at = state.pc + away
            val newInst = state.instructions.toMutableList()
            if (at < newInst.size) {
                newInst[at] = newInst[at].toggle(this)
                return state.copy(newInst).pcDelta(1)
            }
            return state.pcDelta(1)
        }

        override fun toggle(togglingInstruction: AssembunnyInstruction): AssembunnyInstruction =
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

    class Out(val x: String) : AssembunnyInstruction() {
        override fun execute(state: AssembunnyState): AssembunnyState {
            return state.copy(output = state.registers[x[0]]).pcDelta(1)
        }

        override fun toggle(togglingInstruction: AssembunnyInstruction): AssembunnyInstruction =
            this
    }
}