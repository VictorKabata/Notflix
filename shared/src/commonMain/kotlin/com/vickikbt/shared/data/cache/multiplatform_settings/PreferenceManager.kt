@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalSettingsApi::class)

package com.vickikbt.shared.data.cache.multiplatform_settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.getIntOrNullFlow
import com.vickikbt.shared.domain.utils.MultiplatformSettingsWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi

class PreferenceManager constructor(private val multiplatformSettingsWrapper: MultiplatformSettingsWrapper) {

    private val observableSettings = multiplatformSettingsWrapper.createSettings()

    fun setInt(key: String, value: Int) = observableSettings.putInt(key = key, value = value)

    fun getInt(key: String) = observableSettings.getIntOrNullFlow(key = key)

    fun clearPreferences() = observableSettings.clear()

    companion object {
        const val THEME_KEY = "theme"
        const val LANGUAGE_KEY = "language"
        const val IMAGE_QUALITY_KEY = "image_quality"
    }
}
