/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day15Spec : Spek({
    describe("day 15") {
        val sampleInput = listOf(
            "Disc #1 has 5 positions; at time=0, it is at position 4.",
            "Disc #2 has 2 positions; at time=0, it is at position 1."
        )

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day15(sampleInput).solvePart1()).isEqualTo(5)
            }

            it("should answer the question with provided input") {
                assertThat(Day15(Common.readFile("day_15_input.txt")).solvePart1()).isEqualTo(317371)
            }
        }

        describe("part 2") {
            it("should answer the question with provided input") {
                assertThat(Day15(Common.readFile("day_15_input.txt")).solvePart2()).isEqualTo(2080951)
            }
        }
    }
})

