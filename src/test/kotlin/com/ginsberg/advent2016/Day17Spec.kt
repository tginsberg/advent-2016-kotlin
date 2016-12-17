/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day17Spec : Spek({
    describe("day 17") {
        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day17("ihgpwlah").solvePart1()).isEqualTo("DDRRRD")
                assertThat(Day17("kglvqrro").solvePart1()).isEqualTo("DDUDRLRRUDRD")
                assertThat(Day17("ulqzkmiv").solvePart1()).isEqualTo("DRURDRUDDLLDLUURRDULRLDUUDDDRR")
            }

            it("should answer the question with provided input") {
                assertThat(Day17("lpvhkcbi").solvePart1()).isEqualTo("DUDRLRRDDR")
            }
        }

        describe("part 2") {
            it("should answer example inputs properly") {
                assertThat(Day17("ihgpwlah").solvePart2()).isEqualTo(370)
                assertThat(Day17("kglvqrro").solvePart2()).isEqualTo(492)
                assertThat(Day17("ulqzkmiv").solvePart2()).isEqualTo(830)
            }

            it("should answer the question with provided input") {
                assertThat(Day17("lpvhkcbi").solvePart2()).isEqualTo(788)
            }
        }
    }
})

