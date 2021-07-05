package com.vickikbt.notflix

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

@HiltAndroidApp
class NotflixApplication : Application() {

    @Inject
    //lateinit var themeDataStore: ThemeDatastore

    override fun onCreate() {
        super.onCreate()

        //Initialise Timber for logging
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())

    }

    init {
        //Coroutines.main { initTheme() }
    }

    /*private suspend fun initTheme() {
        val value = themeDataStore.getSavedTheme()
        value.collect { theme ->
            AppCompatDelegate.setDefaultNightMode(
                theme ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )
        }
    }*/

}