/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day25Spec : Spek({
    describe("day 25") {

        describe("part 1") {
            it("should answer the question with provided input") {
                assertThat(Day25(Common.readFile("day_25_input.txt")).solvePart1()).isEqualTo(175)
            }
        }
    }
})

