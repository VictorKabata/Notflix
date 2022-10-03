package com.vickikbt.shared.di

import com.vickikbt.shared.domain.utils.DatabaseDriverFactory
import com.vickikbt.shared.domain.utils.MultiplatformSettingsWrapper
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory(context = get()) }

    single { MultiplatformSettingsWrapper(context = get()).createSettings() }
}
