package com.vickikbt.notflix

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.vickikbt.cache.di.cacheModule
import com.vickikbt.network.di.networkModule
import com.vickikbt.notflix.di.presentationModule
import com.vickikbt.repository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@ExperimentalPagingApi
class NotflixApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            val modules = listOf(networkModule, cacheModule, repositoryModule, presentationModule)

            androidLogger(Level.NONE)
            androidContext(this@NotflixApplication)
            modules(modules)
        }
    }
}
