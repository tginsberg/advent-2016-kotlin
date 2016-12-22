/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day21Spec : Spek({
    describe("day 21") {
        val sampleInput = listOf(
            "swap position 4 with position 0",
            "swap letter d with letter b",
            "reverse positions 0 through 4",
            "rotate left 1 step",
            "move position 1 to position 4",
            "move position 3 to position 0",
            "rotate based on position of letter b",
            "rotate based on position of letter d"
        )

        describe("transforms") {
            val day = Day21(listOf())
            it("swaps positions X and Y") {
                assertThat(day.swapPosition("abc", 0, 2)).isEqualTo("cba")
                assertThat(day.swapPosition("abcde", 4, 0)).isEqualTo("ebcda")
            }
            it("swaps letters X and Y") {
                assertThat(day.swapLetters("abc", 'a', 'd')).isEqualTo("dbc")
                assertThat(day.swapLetters("ebcda", 'd', 'b')).isEqualTo("edcba")
            }
            it("rotate shift left") {
                assertThat(day.rotateShift("abcdef", "left", 2)).isEqualTo("cdefab")
                assertThat(day.rotateShift("abcde", "left", 1)).isEqualTo("bcdea")
            }
            it("rotate shift right") {
                assertThat(day.rotateShift("abcdef", "right", 2)).isEqualTo("efabcd")
            }
            it("rotate letter") {
                assertThat(day.rotateLetter("abcdef", 'c', true)).isEqualTo("defabc")
                assertThat(day.rotateLetter("abcdefghi", 'e', true)).isEqualTo("defghiabc")
                assertThat(day.rotateLetter("abdec", 'b', true)).isEqualTo("ecabd")
                assertThat(day.rotateLetter("ecabd", 'd', true)).isEqualTo("decab")
            }
            it("reverse range") {
                assertThat(day.reverse("abcdefghi", 2, 5)).isEqualTo("abfedcghi")
                assertThat(day.reverse("abcdefghi", 0, 8)).isEqualTo("ihgfedcba")
                assertThat(day.reverse("edcba", 0, 4)).isEqualTo("abcde")
            }
            it("moves chars") {
                assertThat(day.move("abcdefghi", 2, 5)).isEqualTo("abdefcghi")
                assertThat(day.move("abcdefghi", 5, 2)).isEqualTo("abfcdeghi")
                assertThat(day.move("bcdea", 1, 4)).isEqualTo("bdeac")
                assertThat(day.move("bdeac", 3, 0)).isEqualTo("abdec")
            }
        }

        describe("part 1") {

            it("should answer example inputs properly") {
                assertThat(Day21(sampleInput.take(1)).solvePart1("abcde")).isEqualTo("ebcda")
                assertThat(Day21(sampleInput.take(2)).solvePart1("abcde")).isEqualTo("edcba")
                assertThat(Day21(sampleInput.take(3)).solvePart1("abcde")).isEqualTo("abcde")
                assertThat(Day21(sampleInput.take(4)).solvePart1("abcde")).isEqualTo("bcdea")
                assertThat(Day21(sampleInput.take(5)).solvePart1("abcde")).isEqualTo("bdeac")
                assertThat(Day21(sampleInput.take(6)).solvePart1("abcde")).isEqualTo("abdec")
                assertThat(Day21(sampleInput.take(7)).solvePart1("abcde")).isEqualTo("ecabd")
                assertThat(Day21(sampleInput).solvePart1("abcde")).isEqualTo("decab")
            }

            it("should answer the question with provided input") {
                assertThat(Day21(Common.readFile("day_21_input.txt")).solvePart1("abcdefgh")).isEqualTo("fdhbcgea")
            }
        }

        describe("part 2") {
            it("should answer the question with provided input") {
                assertThat(Day21(Common.readFile("day_21_input.txt")).solvePart2("fbgdceah")).isEqualTo("egfbcadh")
            }
        }
    }
})

