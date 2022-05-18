package com.vickikbt.shared.data.cache.multiplatform_settings

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.flowOf

class PreferenceManager constructor(private val settings: Settings) {

    fun setString(key: String, value: String) = settings.putString(key, value)

    fun getString(key: String) = flowOf(settings.getStringOrNull(key))

    fun setLong(key: String, value: Long) = settings.putLong(key, value)

    fun getLong(key: String) = flowOf(settings.getLongOrNull(key))

    fun setInt(key: String, value: Int) = settings.putInt(key, value)

    fun getInt(key: String) = flowOf(settings.getIntOrNull(key))

    fun clearPreferences() = settings.clear()
}
