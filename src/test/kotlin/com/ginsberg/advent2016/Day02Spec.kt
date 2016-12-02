/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day02Spec : Spek({
    describe("day 2") {
        val sampleInput = listOf("ULL","RRDDD","LURDL","UUUUD")

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day02(sampleInput).solvePart1()).isEqualTo("1985")
            }

            it("should answer the question with provided input") {
                assertThat(Day02(Common.readFile("day_02_input.txt")).solvePart1()).isEqualTo("78985")
            }
        }

        describe("part 2") {
            it("should answer example input properly") {
                assertThat(Day02(sampleInput).solvePart2()).isEqualTo("5DB3")
            }

            it("should answer the question with provided input") {
                assertThat(Day02(Common.readFile("day_02_input.txt")).solvePart2()).isEqualTo("57DD8")
            }
        }

    }
})