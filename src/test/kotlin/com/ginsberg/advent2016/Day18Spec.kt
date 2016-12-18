/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day18Spec : Spek({
    describe("day 18") {
        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day18("..^^.").solve(3)).isEqualTo(6)
                assertThat(Day18(".^^.^.^^^^").solve(10)).isEqualTo(38)
            }

            it("should answer the question with provided input") {
                assertThat(Day18(Common.fileToString("day_18_input.txt")).solve(40)).isEqualTo(1926)
            }
        }

        describe("part 2") {
            it("should answer the question with provided input") {
                assertThat(Day18(Common.fileToString("day_18_input.txt")).solve(400000)).isEqualTo(19986699)
            }
        }
    }
})

