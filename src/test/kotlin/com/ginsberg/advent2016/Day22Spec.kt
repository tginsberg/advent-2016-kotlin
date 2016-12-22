/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day22Spec : Spek({
    describe("day 22") {
        val sampleInput = listOf(
            "/dev/grid/node-x0-y0     50T   49T    1T   72%",
            "/dev/grid/node-x0-y1     100T   10T    90T   78%"
        )

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day22(sampleInput).solvePart1()).isEqualTo(1)
            }

            it("should answer the question with provided input") {
                assertThat(Day22(Common.readFile("day_22_input.txt")).solvePart1()).isEqualTo(941)
            }
        }
    }
})

