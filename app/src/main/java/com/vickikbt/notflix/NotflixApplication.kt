package com.vickikbt.notflix

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class NotflixApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Initialise Timber for logging
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

}