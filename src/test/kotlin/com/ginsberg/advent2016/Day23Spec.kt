/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xit

class Day23Spec : Spek({
    describe("day 23") {
        val sampleInput = listOf(
            "cpy 2 a",
            "tgl a",
            "tgl a",
            "tgl a",
            "cpy 1 a",
            "dec a",
            "dec a"
        )

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day23(sampleInput).solvePart1()).isEqualTo(3)
            }

            it("should answer the question with provided input") {
                assertThat(Day23(Common.readFile("day_23_input.txt")).solvePart1()).isEqualTo(12762)
            }
        }

        describe("part 2") {

            // Run this manually, it takes forever.
            xit("should answer the question with provided input") {
                assertThat(Day23(Common.readFile("day_23_input.txt")).solvePart2()).isEqualTo(479009322)
            }
        }
    }
})

