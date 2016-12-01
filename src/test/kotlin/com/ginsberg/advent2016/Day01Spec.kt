/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day01Spec : Spek({
    describe("day 1") {

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day01("R2, L3").solvePart1()).isEqualTo(5)
                assertThat(Day01("R2, R2, R2").solvePart1()).isEqualTo(2)
                assertThat(Day01("R5, L5, R5, R3").solvePart1()).isEqualTo(12)
            }

            it("should answer the question with provided input") {
                assertThat(Day01(Common.fileToString("day_01_input.txt")).solvePart1()).isEqualTo(332)
            }
        }

        describe("part 2") {
            it("should answer example input properly") {
                assertThat(Day01("R8, R4, R4, R8").solvePart2()).isEqualTo(4)
            }

            it("should answer the question with provided input") {
                assertThat(Day01(Common.fileToString("day_01_input.txt")).solvePart2()).isEqualTo(166)
            }
        }

    }
})