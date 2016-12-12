/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day12Spec : Spek({
    describe("day 12") {

        val sampleInput = listOf(
            "cpy 41 a",
            "inc a",
            "inc a",
            "dec a",
            "jnz a 2",
            "dec a")

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day12(sampleInput).solvePart1()).isEqualTo(42)
            }

            it("should answer the question with provided input") {
                assertThat(Day12(Common.readFile("day_12_input.txt")).solvePart1()).isEqualTo(318007)
            }
        }

        describe("part 2") {
            it("should answer the question with provided input") {
                assertThat(Day12(Common.readFile("day_12_input.txt")).solvePart2()).isEqualTo(9227661)
            }
        }
    }
})

