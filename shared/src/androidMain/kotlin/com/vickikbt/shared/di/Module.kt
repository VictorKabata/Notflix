package com.vickikbt.shared.di

import com.vickikbt.shared.domain.utils.MultiplatformSettingsWrapper
import io.ktor.client.engine.android.*
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { MultiplatformSettingsWrapper(context = get()).createSettings() }
    single { Android.create()}
}
