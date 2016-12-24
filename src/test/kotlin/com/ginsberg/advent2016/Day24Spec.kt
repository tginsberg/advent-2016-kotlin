/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day24Spec : Spek({
    describe("day 24") {
        val sampleInput = listOf(
            "###########",
            "#0.1.....2#",
            "#.#######.#",
            "#4.......3#",
            "###########"
        )

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day24(sampleInput).solvePart1()).isEqualTo(14)
            }

            it("should answer the question with provided input") {
                assertThat(Day24(Common.readFile("day_24_input.txt")).solvePart1()).isEqualTo(412)

            }
        }

        describe("part 2") {
            it("should answer the question with provided input") {
                assertThat(Day24(Common.readFile("day_24_input.txt")).solvePart2()).isEqualTo(664)
            }
        }
    }
})

