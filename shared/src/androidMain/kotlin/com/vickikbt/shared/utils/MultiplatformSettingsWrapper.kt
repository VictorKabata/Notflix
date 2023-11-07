package com.vickikbt.shared.utils

import android.content.Context
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.ObservableSettings

actual class MultiplatformSettingsWrapper {
    val context = ContextUtils.context

    actual fun createSettings(): ObservableSettings {
        val sharedPreferences =
            context.getSharedPreferences("notflix_preferences", Context.MODE_PRIVATE)
        return AndroidSettings(delegate = sharedPreferences)
    }
}
