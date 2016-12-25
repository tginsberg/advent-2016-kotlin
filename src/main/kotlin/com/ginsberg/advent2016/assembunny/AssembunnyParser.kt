package com.ginsberg.advent2016.assembunny


object AssembunnyParser {
    private val COPY = Regex("""^cpy (\S+) (\S+)$""")
    private val INC = Regex("""^inc ([abcd])$""")
    private val DEC = Regex("""^dec ([abcd])$""")
    private val JUMP = Regex("""^jnz (\S+) (\S+)$""")
    private val TOGGLE = Regex("""^tgl ([abcd])$""")
    private val OUT = Regex("""^out ([abcd])$""")

    fun parseInstructions(instructions: List<String>): List<AssembunnyInstruction> {
        return instructions.map {
            when {
                COPY.matches(it) -> {
                    val (x, y) = COPY.matchEntire(it)?.destructured!!
                    AssembunnyInstruction.Copy(x, y)
                }
                INC.matches(it) -> {
                    val (x) = INC.matchEntire(it)?.destructured!!
                    AssembunnyInstruction.Inc(x)
                }
                DEC.matches(it) -> {
                    val (x) = DEC.matchEntire(it)?.destructured!!
                    AssembunnyInstruction.Dec(x)
                }
                JUMP.matches(it) -> {
                    val (x, y) = JUMP.matchEntire(it)?.destructured!!
                    AssembunnyInstruction.Jump(x, y)
                }
                TOGGLE.matches(it) -> {
                    val (x) = TOGGLE.matchEntire(it)?.destructured!!
                    AssembunnyInstruction.Toggle(x)
                }
                OUT.matches(it) -> {
                    val (x) = OUT.matchEntire(it)?.destructured!!
                    AssembunnyInstruction.Out(x)
                }
                else -> AssembunnyInstruction.Jump("0", "0")
            }
        }
    }

}