/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day03Spec : Spek({
    describe("day 3") {

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day03("  15  5  25").solvePart1()).isEqualTo(0)
                assertThat(Day03("  3   4   5").solvePart1()).isEqualTo(1)
            }

            it("should answer the question with provided input") {
                assertThat(Day03(Common.fileToString("day_03_input.txt")).solvePart1()).isEqualTo(862)
            }
        }

        describe("part 2") {
            it("should answer example input properly") {
                assertThat(Day03("  15  1   2\n5  3  4\n  25  5  6").solvePart2()).isEqualTo(0)
                assertThat(Day03("  3  0   0\n4  0  0\n  5  0  0").solvePart2()).isEqualTo(1)
            }

            it("should answer the question with provided input") {
                assertThat(Day03(Common.fileToString("day_03_input.txt")).solvePart2()).isEqualTo(1577)
            }
        }

    }
})

