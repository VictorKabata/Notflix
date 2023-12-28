package com.vickbt.shared.utils

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.PreferencesSettings
import java.util.prefs.Preferences

actual class MultiplatformSettingsWrapper {
    actual fun createSettings(): ObservableSettings {
        val preferences: Preferences = Preferences.userRoot()
        return PreferencesSettings(delegate = preferences)
    }
}
