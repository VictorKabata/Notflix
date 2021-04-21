package com.vickikbt.notflix

import android.app.Application
import androidx.databinding.library.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class NotflixApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Sets up Timber for logging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}