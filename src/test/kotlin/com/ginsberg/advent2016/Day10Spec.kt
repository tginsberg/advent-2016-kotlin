/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day10Spec : Spek({
    describe("day 10") {

        val sampleCommands = listOf(
            "value 5 goes to bot 2",
            "bot 2 gives low to bot 1 and high to bot 0",
            "value 3 goes to bot 1",
            "bot 1 gives low to output 1 and high to bot 0",
            "bot 0 gives low to output 2 and high to output 0",
            "value 2 goes to bot 2")

        describe("part 1") {
            it("should answer example inputs properly") {
                val day = Day10(sampleCommands, setOf(2,5))
                assertThat(day.solvePart1()).isEqualTo("2")
                assertThat(day.outputs["0"]?.inventory).contains(5)
                assertThat(day.outputs["1"]?.inventory).contains(2)
                assertThat(day.outputs["2"]?.inventory).contains(3)
            }

            it("should answer the question with provided input") {
                assertThat(Day10(Common.readFile("day_10_input.txt")).solvePart1()).isEqualTo("86")
            }
        }

        describe("part 2") {
            it("should answer example input properly") {
                assertThat(Day10(sampleCommands, setOf(2,5)).solvePart2()).isEqualTo(30)
            }

            it("should answer the question with provided input") {
                assertThat(Day10(Common.readFile("day_10_input.txt")).solvePart2()).isEqualTo(22847)

            }
        }
    }
})

