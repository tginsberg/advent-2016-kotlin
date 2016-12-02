/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016.utils

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class ExtensionsSpec : Spek({
    describe("Int.toHex()") {
        it("should convert positive integers to HEX") {
            assertThat(1.toHex()).isEqualTo("1")
            assertThat(10.toHex()).isEqualTo("A")
            assertThat(11.toHex()).isEqualTo("B")
            assertThat(12.toHex()).isEqualTo("C")
            assertThat(13.toHex()).isEqualTo("D")
            assertThat(14.toHex()).isEqualTo("E")
            assertThat(15.toHex()).isEqualTo("F")
            assertThat(255.toHex()).isEqualTo("FF")
        }
    }
})