/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day04Spec : Spek({
    describe("day 4") {

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day04("aaaaa-bbb-z-y-x-123[abxyz]").solvePart1()).isEqualTo(123)
                assertThat(Day04("a-b-c-d-e-f-g-h-987[abcde]").solvePart1()).isEqualTo(987)
                assertThat(Day04("not-a-real-room-404[oarel]").solvePart1()).isEqualTo(404)
                assertThat(Day04("totally-real-room-200[decoy]").solvePart1()).isEqualTo(0)
            }

            it("should answer the question with provided input") {
                assertThat(Day04(Common.fileToString("day_04_input.txt", "\n")).solvePart1()).isEqualTo(173787)
            }
        }

        describe("part 2") {
            it("should answer example input properly") {
                assertThat(Day04("qzmt-zixmtkozy-ivhz-343[zimth]").solvePart2("very encrypted name")).isEqualTo(343)
            }

            it("should answer the question with provided input") {
                assertThat(Day04(Common.fileToString("day_04_input.txt", "\n")).solvePart2()).isEqualTo(548)
            }
        }

    }
})

