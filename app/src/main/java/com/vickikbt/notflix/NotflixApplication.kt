package com.vickikbt.notflix

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotflixApplication:Application() {

    override fun onCreate() {
        super.onCreate()
    }

}