/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day16Spec : Spek({
    describe("day 16") {
        describe("data stream") {
            it("should generate example inputs properly") {
                val d = Day16("")
                assertThat(d.dataStream("1").first()).isEqualTo("100")
                assertThat(d.dataStream("0").first()).isEqualTo("001")
                assertThat(d.dataStream("11111").first()).isEqualTo("11111000000")
                assertThat(d.dataStream("111100001010").first()).isEqualTo("1111000010100101011110000")
            }
        }

        describe("checksum") {
            it("should generate example inputs properly") {
                val d = Day16("")
                assertThat(d.checksum("110010110100")).isEqualTo("100")
            }
        }

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day16("10000").solve(20)).isEqualTo("01100")
            }

            it("should answer the question with provided input") {
                assertThat(Day16("10011111011011001").solve(272)).isEqualTo("10111110010110110")

            }
        }

        describe("part 2") {
            it("should answer the question with provided input") {
                assertThat(Day16("10011111011011001").solve(35651584)).isEqualTo("01101100001100100")
            }
        }
    }
})

