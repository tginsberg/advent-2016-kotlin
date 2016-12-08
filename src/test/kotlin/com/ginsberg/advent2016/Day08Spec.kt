/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016

import com.ginsberg.advent2016.utils.Common
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class Day08Spec : Spek({
    describe("day 8") {

        describe("part 1") {
            it("should answer example inputs properly") {
                val result = Day08(
                    listOf(
                        "rect 3x2",
                        "rotate column x=1 by 1",
                        "rotate row y=0 by 4",
                        "rotate column x=1 by 1"
                    ), 3, 7
                )
                assertThat(result.solvePart1()).isEqualTo(6)
                assertThat(result.solvePart2().split("\n")[0]).isEqualTo(" #  # #") // Representative sample.
            }

            it("should answer the question with provided input") {
                assertThat(Day08(Common.readFile("day_08_input.txt")).solvePart1()).isEqualTo(115)
            }
        }

        describe("part 2") {
            it("should answer the question with provided input") {
                // Yup, this is super ugly. I normally wouldn't write a test like this, but
                // since I'm doing a visual inspection of the results for Part 2 (rather than
                // write some kind of font parser), this will ensure that I don't mess up the
                // code while refactoring it.
                val result = Day08(Common.readFile("day_08_input.txt")).solvePart2().split("\n")
                assertThat(result[0]).isEqualTo("#### #### #### #   ##  # #### ###  ####  ###   ## ")
                assertThat(result[1]).isEqualTo("#    #    #    #   ## #  #    #  # #      #     # ")
                assertThat(result[2]).isEqualTo("###  ###  ###   # # ##   ###  #  # ###    #     # ")
                assertThat(result[3]).isEqualTo("#    #    #      #  # #  #    ###  #      #     # ")
                assertThat(result[4]).isEqualTo("#    #    #      #  # #  #    # #  #      #  #  # ")
                assertThat(result[5]).isEqualTo("#### #    ####   #  #  # #    #  # #     ###  ##  ")
            }
        }

    }
})

