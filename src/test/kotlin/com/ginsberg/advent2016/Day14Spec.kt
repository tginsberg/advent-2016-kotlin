/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day14Spec : Spek({
    describe("day 14") {
        describe("regexes") {
            val testGroups3 = (('0'..'9') + ('a'..'f')).map { "$it$it$it" }
            val testGroups5 = (('0'..'9') + ('a'..'f')).map { "$it$it$it$it$it" }

            it("should detect three similar characters") {
                testGroups3.forEach {
                    assertThat(Day14.threeDigits.matches(it)).isTrue()
                }
            }
            it("should match the very first grouping of three") {
                assertThat(Day14.threeDigits.matchEntire("175697873eccc49ac690322111307eb7")?.destructured!!.component1()).isEqualTo("c")
            }
            it("should identify the group of three") {
                testGroups3.forEach {
                    assertThat(Day14.threeDigits.matchEntire(it)?.destructured!!.component1()).isEqualTo(it[0].toString())
                }
            }
            it("should detect five similar characters") {
                testGroups5.forEach {
                    assertThat(Day14.fiveDigits.matches(it)).isTrue()
                }
            }
            it("should identify the group of five") {
                testGroups5.forEach {
                    assertThat(Day14.fiveDigits.matchEntire(it)?.destructured!!.component1()).isEqualTo(it[0].toString())
                }
            }
        }

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day14("abc", 1).solvePart1()).isEqualTo(39)
                assertThat(Day14("abc", 2).solvePart1()).isEqualTo(92)
                assertThat(Day14("abc", 64).solvePart1()).isEqualTo(22728)
            }

            it("should answer the question with provided input") {
                assertThat(Day14("ahsbgdzn").solvePart1()).isEqualTo(23890)
            }
        }

        describe("part 2") {
            it("should answer example inputs properly") {
                assertThat(Day14("abc", 1).solvePart2()).isEqualTo(10)
                assertThat(Day14("abc", 64).solvePart2()).isEqualTo(22551)
            }

            it("should answer the question with provided input") {
                assertThat(Day14("ahsbgdzn", 64).solvePart2()).isEqualTo(22696)
            }
        }
    }
})

