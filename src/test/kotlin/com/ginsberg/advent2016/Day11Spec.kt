/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day11Spec : Spek({
    describe("day 11") {

        describe("part 1") {
            it("should answer the question with provided input") {
                assertThat(Day11(listOf(8,2,0,0)).solvePart1()).isEqualTo(47)
            }
        }

        describe("part 2") {
            it("should answer the question with provided input") {
                assertThat(Day11(listOf(12,2,0,0)).solvePart1()).isEqualTo(71)
            }
        }
    }
})

