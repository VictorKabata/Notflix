package com.vickbt.notflix

import android.app.Application
import com.vickbt.shared.di.initKoin
import com.vickbt.shared.utils.ContextUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class NotflixApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ContextUtils.setContext(context = this)

        initKoin {
            androidLogger(level = Level.NONE)
            androidContext(androidContext = this@NotflixApplication)
        }
    }
}
