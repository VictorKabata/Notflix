package com.vickikbt.notflix

import android.app.Application
import com.vickikbt.shared.di.initKoin
import com.vickikbt.shared.utils.ContextUtils
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class NotflixApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ContextUtils.setContext(context = this)

        initKoin {
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@NotflixApplication)
        }
    }
}
