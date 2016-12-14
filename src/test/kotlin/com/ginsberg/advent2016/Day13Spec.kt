/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day13Spec : Spek({
    describe("day 13") {
        describe("open/closed") {
            it("should calculate open/closed according to the example") {
                assertThat(Day13.Node(0, 0).isOpen()).isTrue()
                assertThat(Day13.Node(1, 0).isOpen()).isFalse()
                assertThat(Day13.Node(2, 0).isOpen()).isTrue()
                assertThat(Day13.Node(3, 0).isOpen()).isFalse()
                assertThat(Day13.Node(4, 0).isOpen()).isFalse()
                assertThat(Day13.Node(5, 0).isOpen()).isFalse()
                assertThat(Day13.Node(6, 0).isOpen()).isFalse()
                assertThat(Day13.Node(7, 0).isOpen()).isTrue()
                assertThat(Day13.Node(8, 0).isOpen()).isFalse()
                assertThat(Day13.Node(9, 0).isOpen()).isFalse()

                assertThat(Day13.Node(0, 6).isOpen()).isFalse()
                assertThat(Day13.Node(1, 6).isOpen()).isTrue()
                assertThat(Day13.Node(2, 6).isOpen()).isTrue()
                assertThat(Day13.Node(3, 6).isOpen()).isTrue()
                assertThat(Day13.Node(4, 6).isOpen()).isFalse()
                assertThat(Day13.Node(5, 6).isOpen()).isFalse()
                assertThat(Day13.Node(6, 6).isOpen()).isTrue()
                assertThat(Day13.Node(7, 6).isOpen()).isFalse()
                assertThat(Day13.Node(8, 6).isOpen()).isFalse()
                assertThat(Day13.Node(9, 6).isOpen()).isFalse()
            }
        }

        describe("part 1") {
            it("should answer example inputs properly") {
                assertThat(Day13(10, 7, 4).solvePart1()).isEqualTo(11)
            }

            it("should answer the question with provided input") {
                assertThat(Day13(1352, 31, 39).solvePart1()).isEqualTo(90)
            }
        }

        describe("part 2") {
            it("should answer example inputs properly") {
                assertThat(Day13(10, 7, 4, 1).solvePart2()).isEqualTo(3)
                assertThat(Day13(10, 7, 4, 2).solvePart2()).isEqualTo(5)
                assertThat(Day13(10, 7, 4, 3).solvePart2()).isEqualTo(6)
                assertThat(Day13(10, 7, 4, 4).solvePart2()).isEqualTo(9)
            }

            it("should answer the question with provided input") {
                assertThat(Day13(1352, 7, 4, 50).solvePart2()).isEqualTo(135)
            }
        }
    }
})

