/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day20Spec : Spek({
    describe("day 20") {
        val sampleInput = listOf("5-8", "0-2", "4-7")
        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day20(sampleInput).solvePart1()).isEqualTo(3)
                assertThat(Day20(listOf("9-11", "0-2", "3-7")).solvePart1()).isEqualTo(8)
            }

            it("should answer the question with provided input") {
                assertThat(Day20(Common.readFile("day_20_input.txt")).solvePart1()).isEqualTo(4793564)
            }
        }

        describe("part 2") {
            it("should answer example inputs properly") {
                assertThat(Day20(sampleInput).solvePart2(9)).isEqualTo(2)
                assertThat(Day20(sampleInput).solvePart2(10)).isEqualTo(3)

            }
            it("should answer the question with provided input") {
                assertThat(Day20(Common.readFile("day_20_input.txt")).solvePart2(4294967295L)).isEqualTo(146)
            }
        }
    }
})

