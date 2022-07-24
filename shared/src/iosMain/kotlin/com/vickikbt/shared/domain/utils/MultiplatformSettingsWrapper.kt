package com.vickikbt.shared.domain.utils

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.ObservableSettings
import platform.Foundation.NSUserDefaults

actual class MultiplatformSettingsWrapper {
    actual fun createSettings(): ObservableSettings {
        val nsUserDefault=NSUserDefaults.standardUserDefaults
        return AppleSettings(delegate = nsUserDefault)
    }
}
