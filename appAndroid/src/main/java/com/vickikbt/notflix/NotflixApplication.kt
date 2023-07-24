package com.vickikbt.notflix

import android.app.Application
import com.vickikbt.notflix.di.presentationModule
import com.vickikbt.shared.di.initKoin
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class NotflixApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = listOf(presentationModule)
        initKoin {
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@NotflixApplication)
            modules(appModules)
        }
    }
}
