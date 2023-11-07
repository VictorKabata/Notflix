package com.vickikbt.shared.di

import com.vickikbt.shared.utils.MultiplatformSettingsWrapper
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { MultiplatformSettingsWrapper().createSettings() }
}
