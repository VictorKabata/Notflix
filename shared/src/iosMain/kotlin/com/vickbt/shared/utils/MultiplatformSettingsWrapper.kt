package com.vickbt.shared.utils

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings
import platform.Foundation.NSUserDefaults

actual class MultiplatformSettingsWrapper {
    actual fun createSettings(): ObservableSettings {
        val nsUserDefault = NSUserDefaults.standardUserDefaults
        return NSUserDefaultsSettings(delegate = nsUserDefault)
    }
}
