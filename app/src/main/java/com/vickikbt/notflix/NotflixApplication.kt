package com.vickikbt.notflix

import android.app.Application
import com.vickikbt.cache.di.cacheModule
import com.vickikbt.network.di.networkModule
import com.vickikbt.repository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import timber.log.Timber.DebugTree

class NotflixApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        initKoin()

        //Initialise Timber for logging
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

}