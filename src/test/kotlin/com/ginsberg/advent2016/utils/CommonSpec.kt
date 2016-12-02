/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016.utils

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class CommonSpec : Spek({
    describe("load from file to String") {
        it("should concat lines to a single String") {
            assertThat(Common.fileToString("read_file_test_1.txt")).isEqualTo("ABC")
        }
        it("should concat lines to a single String, delimited as requested") {
            assertThat(Common.fileToString("read_file_test_1.txt", "::")).isEqualTo("A::B::C")
        }
    }

    describe("load file to List of String") {
        it("should read the file properly") {
            assertThat(Common.readFile("read_file_test_1.txt")).isEqualTo(listOf("A", "B", "C"))
        }
    }
})