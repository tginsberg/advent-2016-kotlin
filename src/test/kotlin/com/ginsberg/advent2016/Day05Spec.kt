/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day05Spec : Spek({
    describe("day 5") {

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day05("abc").solvePart1()).isEqualTo("18f47a30")
            }

            it("should answer the question with provided input") {
                assertThat(Day05("ffykfhsq").solvePart1()).isEqualTo("c6697b55")
            }
        }

        describe("part 2") {
            it("should answer example input properly") {
                assertThat(Day05("abc").solvePart2()).isEqualTo("05ace8e3")
            }

            it("should answer the question with provided input") {
                assertThat(Day05("ffykfhsq").solvePart2()).isEqualTo("8c35d1ab")
            }
        }

    }
})

