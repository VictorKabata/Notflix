package com.vickikbt.shared.utils

import com.russhwolf.settings.MockSettings
import com.vickikbt.shared.data.cache.multiplatformsettings.PreferenceManager
import com.vickikbt.shared.data.datasources.SettingsRepositoryImpl
import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SharedExtensionsTests {

    private val preferenceManager = PreferenceManager(MockSettings())
    private val settingsPresenterTest =
        SharedSettingsPresenter(settingsRepository = SettingsRepositoryImpl(preferenceManager = preferenceManager))

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

    @Test
    fun `getMovieDuration returns correct time with single hour`() {
        val subject = 90
        val expected = "1hr 30mins"

        assertEquals(expected = expected, actual = subject.getMovieDuration())
    }

    @Test
    fun `getMovieDuration returns correct time with multiple hours`() {
        val subject = 260
        val expected = "4hrs 20mins"

        assertEquals(expected = expected, actual = subject.getMovieDuration())
    }

    @Test
    fun `getMovieDuration returns null with null`() {
        val subject: Int? = null

        assertNull(subject.getMovieDuration())
    }

    @Test
    fun `getPopularity returns correct percentage`() {
        val subject = 8.0
        val expected = "80"

        assertEquals(expected = expected, actual = subject.getPopularity())
    }

    @Test
    fun `getRating returns correct rating`() {
        val subject = 8.0
        val expected = "4.0"

        assertEquals(expected = expected, actual = subject.getRating())
    }

    @Test
    fun `getAppLanguage returns default language value`() = runTest {
        val subject = getAppLanguage(settingsPresenter = settingsPresenterTest)

        assertEquals(expected = "en", actual = subject)
    }
}
