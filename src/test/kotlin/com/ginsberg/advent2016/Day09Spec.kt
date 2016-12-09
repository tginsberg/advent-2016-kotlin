/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day09Spec : Spek({
    describe("day 9") {

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day09("ADVENT").solvePart1()).isEqualTo("ADVENT".length)
                assertThat(Day09("A(1x5)BC").solvePart1()).isEqualTo("ABBBBBC".length)
                assertThat(Day09("(3x3)XYZ").solvePart1()).isEqualTo("XYZXYZXYZ".length)
                assertThat(Day09("(6x1)(1x3)A").solvePart1()).isEqualTo("(1x3)A".length)
                assertThat(Day09("X(8x2)(3x3)ABCY").solvePart1()).isEqualTo("X(3x3)ABC(3x3)ABCY".length)
            }

            it("should answer the question with provided input") {
                assertThat(Day09(Common.fileToString("day_09_input.txt")).solvePart1()).isEqualTo(183269)
            }
        }

        describe("part 2") {
            it("should answer example input properly") {
                assertThat(Day09("ADVENT").solvePart2()).isEqualTo("ADVENT".length.toLong())
                assertThat(Day09("(3x3)XYZ").solvePart2()).isEqualTo("XYZXYZXYZ".length.toLong())
                assertThat(Day09("X(8x2)(3x3)ABCY").solvePart2()).isEqualTo("XABCABCABCABCABCABCY".length.toLong())
                assertThat(Day09("(27x12)(20x12)(13x14)(7x10)(1x12)A").solvePart2()).isEqualTo(241920L)
                assertThat(Day09("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN").solvePart2()).isEqualTo(445L)
            }

            it("should answer the question with provided input") {
                assertThat(Day09(Common.fileToString("day_09_input.txt")).solvePart2()).isEqualTo(11317278863L)
            }
        }
    }
})

