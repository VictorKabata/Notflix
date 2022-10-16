package com.vickikbt.shared.data.cache.multiplatformSettings

import com.russhwolf.settings.MockSettings
import com.vickikbt.shared.data.cache.multiplatformsettings.PreferenceManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class PreferenceManagerTests {
    private val observableSettingsMock = MockSettings()

    // Subject under test
    private val preferenceManager: PreferenceManager =
        PreferenceManager(observableSettings = observableSettingsMock)

    @Test
    fun `set int save correct value`() = runTest {
        preferenceManager.setInt(key = "test", value = 1)

        val result = preferenceManager.getInt(key = "test").first()

        assertNotNull(result)
        assertEquals(actual = result, expected = 1)
    }

    @Test
    fun `set int save correct value multiple times`() = runTest {
        (0..11).forEachIndexed { index, i ->
            preferenceManager.setInt(key = "test $index", value = i)

            val result = preferenceManager.getInt(key = "test $index").first()

            assertNotNull(result)
            assertEquals(actual = result, expected = i)
        }
    }

    @Test
    fun `clear preferences clears cache`() = runTest {
        preferenceManager.setInt(key = "test", value = 1)

        val savedValue = preferenceManager.getInt(key = "test").first()

        assertNotNull(savedValue)

        preferenceManager.clearPreferences()

        val result = preferenceManager.getInt(key = "test").first()

        assertNull(result)
    }

    @Test
    fun `clear preferences clears cache for multiple values`() = runTest {
        (0..11).forEachIndexed { index, i ->
            preferenceManager.setInt(key = "test $index", value = i)

            val savedValue = preferenceManager.getInt(key = "test $index").first()

            assertNotNull(savedValue)
        }

        preferenceManager.clearPreferences()

        (0..11).forEachIndexed { index, i ->
            val result = preferenceManager.getInt(key = "test $index").first()
            assertNull(result)
        }
    }
}
