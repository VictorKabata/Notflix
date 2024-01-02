package com.vickbt.shared.utils

import com.russhwolf.settings.ObservableSettings

expect class MultiplatformSettingsWrapper {
    fun createSettings(): ObservableSettings
}
