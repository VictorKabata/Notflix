package com.vickikbt.notflix.di

import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { FirebaseAnalytics.getInstance(androidContext()) }
}
