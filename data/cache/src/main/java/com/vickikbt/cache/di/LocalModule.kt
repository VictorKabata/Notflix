package com.vickikbt.cache.di

import com.vickikbt.cache.preferences.PreferenceManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val cacheModule = module {
    single { PreferenceManager(androidApplication()) }
}
