/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day07Spec : Spek({
    describe("day 7") {

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day07(listOf("abba[mnop]qrst")).solvePart1()).isEqualTo(1)
                assertThat(Day07(listOf("abcd[bddb]xyyx")).solvePart1()).isEqualTo(0)
                assertThat(Day07(listOf("aaaa[qwer]tyui")).solvePart1()).isEqualTo(0)
                assertThat(Day07(listOf("ioxxoj[asdfgh]zxcvbn")).solvePart1()).isEqualTo(1)
            }

            it("should answer the question with provided input") {
                assertThat(Day07(Common.readFile("day_07_input.txt")).solvePart1()).isEqualTo(115)
            }
        }

        describe("part 2") {
            it("should answer example input properly") {
                assertThat(Day07(listOf("aba[bab]xyz")).solvePart2()).isEqualTo(1)
                assertThat(Day07(listOf("xyx[xyx]xyx")).solvePart2()).isEqualTo(0)
                assertThat(Day07(listOf("aaa[kek]eke")).solvePart2()).isEqualTo(1)
                assertThat(Day07(listOf("zazbz[bzb]cdb")).solvePart2()).isEqualTo(1)
            }

            it("should answer the question with provided input") {
                assertThat(Day07(Common.readFile("day_07_input.txt")).solvePart2()).isEqualTo(231)
            }
        }

    }
})

