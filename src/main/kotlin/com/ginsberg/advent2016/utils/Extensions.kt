/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016.utils

import java.math.BigInteger

// -- Hex Conversions
fun ByteArray.toHex(): String = String.format("%032x", BigInteger(1, this))
fun Int.toHex(): String = String.format("%X", this)

// -- Char/Digit Conversions
fun Char.asDigit(): Int = if(this.isDigit()) this-'0' else throw IllegalArgumentException()