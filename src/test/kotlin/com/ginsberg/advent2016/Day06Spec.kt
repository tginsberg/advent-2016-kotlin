/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day06Spec : Spek({
    describe("day 6") {

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day06(Common.readFile("day_06_sample.txt")).solvePart1()).isEqualTo("easter")
            }

            it("should answer the question with provided input") {
                assertThat(Day06(Common.readFile("day_06_input.txt")).solvePart1()).isEqualTo("cyxeoccr")
            }
        }

        describe("part 2") {
            it("should answer example input properly") {
                assertThat(Day06(Common.readFile("day_06_sample.txt")).solvePart2()).isEqualTo("advent")
            }

            it("should answer the question with provided input") {
                assertThat(Day06(Common.readFile("day_06_input.txt")).solvePart2()).isEqualTo("batwpask")

            }
        }

    }
})

