/*
 * Copyright (c) 2016 by Todd Ginsberg
 */

package com.ginsberg.advent2016.utils

import java.io.File

object Common {
    /**
     * Given a file on the classpath, return its contents as one String.
     */
    fun fileToString(fileName: String, separator: String = ""): String =
        File(javaClass.classLoader.getResource(fileName).toURI())
                .readLines()
                .reduce { a, b -> "$a$separator$b" }

    /**
     * Given a file on the classpath, return its contents as a List of Strings.
     */
    fun readFile(fileName: String): List<String> =
            File(javaClass.classLoader.getResource(fileName).toURI()).readLines()
}

