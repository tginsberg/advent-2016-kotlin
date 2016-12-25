package com.ginsberg.advent2016.assembunny

data class AssembunnyState(val instructions: List<AssembunnyInstruction>,
                           val registers: Map<Char, Int>,
                           val pc: Int = 0,
                           val output: Int? = null) {

    fun pcDelta(v: Int) =
        this.copy(pc = pc + v)

    fun isDone(): Boolean =
        instructions.size <= pc

    fun op(): AssembunnyState {
        val nextOp: AssembunnyInstruction = instructions[pc]
        return if (nextOp.isValid()) nextOp.execute(this.copy(output = null))
        else this.copy(output = null).pcDelta(1)
    }
}
