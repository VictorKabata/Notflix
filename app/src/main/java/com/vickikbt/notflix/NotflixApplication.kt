package com.vickikbt.notflix

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.vickikbt.cache.di.cacheModule
import com.vickikbt.cache.preferences.ThemePreferences
import com.vickikbt.network.di.networkModule
import com.vickikbt.repository.di.repositoryModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import timber.log.Timber.DebugTree

class NotflixApplication : Application() {

    private val themePreferences: ThemePreferences by inject()

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initTheme()

        if (BuildConfig.DEBUG) Timber.plant(DebugTree())

    }

    private fun initKoin() {
        startKoin {
            val modules = listOf(networkModule, cacheModule, repositoryModule)

            androidLogger(Level.NONE)
            androidContext(this@NotflixApplication)
            modules(modules)
        }
    }

    private fun initTheme() {
        val appTheme = themePreferences.appTheme
        appTheme.observeForever { theme ->
            when (theme) {
                "light_theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                "dark_theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                "system_default" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //GlobalSplitCompat.install(this)
    }


}