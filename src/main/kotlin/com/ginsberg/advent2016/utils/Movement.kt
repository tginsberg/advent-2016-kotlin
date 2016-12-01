/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016.utils

/**
 * This state machine/sealed class structure represents
 * movement on a grid. I'm breaking this out of Day01 where
 * I originally had it, because:
 *   a) It feels a bit large to be in there.
 *   b) It is its own thing and I'm not really used to Kotlin's
 *      ability to have many classes in one file.
 *   c) I suspect I'll use it again before Advent 2016 is up.
 */
sealed class Movement(var x: Int, var y: Int) {
    companion object {
        fun start(): Movement = FacingNorth()
    }

    abstract fun turn(direction: Char): Movement
    abstract fun move(amount: Int): Movement

    fun distance(): Int = Math.abs(x) + Math.abs(y)

    fun location(): Pair<Int, Int> = Pair(x, y)

    class FacingNorth(x: Int = 0, y: Int = 0) : Movement(x, y) {
        override fun turn(direction: Char): Movement =
                when (direction) {
                    'L' -> FacingWest(x, y)
                    'R' -> FacingEast(x, y)
                    else -> FacingNorth(x, y)
                }

        override fun move(amount: Int): Movement {
            x += amount
            return this
        }
    }

    class FacingSouth(x: Int = 0, y: Int = 0) : Movement(x, y) {
        override fun turn(direction: Char): Movement =
                when (direction) {
                    'L' -> FacingEast(x, y)
                    'R' -> FacingWest(x, y)
                    else -> FacingSouth(x, y)
                }

        override fun move(amount: Int): Movement {
            x -= amount
            return this
        }
    }

    class FacingEast(x: Int = 0, y: Int = 0) : Movement(x, y) {
        override fun turn(direction: Char): Movement =
                when (direction) {
                    'L' -> FacingNorth(x, y)
                    'R' -> FacingSouth(x, y)
                    else -> FacingEast(x, y)
                }

        override fun move(amount: Int): Movement {
            y += amount
            return this
        }
    }

    class FacingWest(x: Int = 0, y: Int = 0) : Movement(x, y) {
        override fun turn(direction: Char): Movement =
                when (direction) {
                    'L' -> FacingSouth(x, y)
                    'R' -> FacingNorth(x, y)
                    else -> FacingWest(x, y)
                }

        override fun move(amount: Int): Movement {
            y -= amount
            return this
        }
    }
}
