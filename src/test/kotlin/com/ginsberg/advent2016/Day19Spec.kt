/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day19Spec : Spek({
    describe("day 19") {
        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day19(5).solvePart1()).isEqualTo(3)
                assertThat(Day19(10).solvePart1()).isEqualTo(5)
            }

            it("should answer the question with provided input") {
                assertThat(Day19(3004953).solvePart1()).isEqualTo(1815603)
            }
        }

        describe("part 2") {
            it("should answer example inputs properly") {
                assertThat(Day19(5).solvePart2()).isEqualTo(2)
            }
            it("should answer the question with provided input") {
                assertThat(Day19(3004953).solvePart2()).isEqualTo(1410630)
            }
        }
    }
})

