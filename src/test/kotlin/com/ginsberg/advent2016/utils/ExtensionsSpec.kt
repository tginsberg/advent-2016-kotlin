/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016.utils

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class ExtensionsSpec : Spek({
    describe("Char.asDigit()") {
        it("should convert each one digit number properly") {
            assertThat("0123456789".map(Char::asDigit)).isEqualTo(listOf(0,1,2,3,4,5,6,7,8,9))
        }

        it("should throw an exception if the char is not a digit") {
            assertThatThrownBy{ 'A'.asDigit() }
        }
    }

    describe("ByteArray.toHex()") {
        it("should convert known byte arrays HEX") {
            assertThat((0..15).map(Int::toByte).toByteArray().toHex()).isEqualTo("000102030405060708090a0b0c0d0e0f")
        }
    }

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