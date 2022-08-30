package com.vickikbt.shared.utils

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SharedExtensionsTests {

    @Test
    fun `capitalizeEachWord capitalizes lowercase strings`() {
        val subject = "the big brown wolf jumped over"
        val expected = "The Big Brown Wolf Jumped Over"

        assertEquals(expected = expected, actual = subject.capitalizeEachWord())
    }

    @Test
    fun `capitalizeEachWord capitalizes uppercase strings`() {
        val subject = "THE BIG BROWN WOLF JUMPED OVER"
        val expected = "The Big Brown Wolf Jumped Over"

        assertEquals(expected = expected, actual = subject.capitalizeEachWord())
    }

    @Test
    fun `capitalizeEachWord capitalizes lowercase & uppercase strings`() {
        val subject = "ThE bIG BroWN wOLF JUMPED ovEr"
        val expected = "The Big Brown Wolf Jumped Over"

        assertEquals(expected = expected, actual = subject.capitalizeEachWord())
    }

    @Test
    fun `capitalizeEachWord returns null on null strings`() {
        val subject: String? = null

        assertNull(subject?.capitalizeEachWord())
    }

    @Test
    fun `getReleaseDate returns correct date`() {
        val subject = "1998-11-19"
        val expected = "19 NOVEMBER, 1998"

        assertEquals(expected = expected, actual = subject.getReleaseDate())
    }

    @Test
    fun `getReleaseDate throws error on incorrect date format`() {

    }

    @Test
    fun `getReleaseDate returns null on null date`() {
        val subject: String? = null

        assertNull(subject?.getReleaseDate())
    }

}
