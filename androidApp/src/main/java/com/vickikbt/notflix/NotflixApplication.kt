package com.vickikbt.notflix

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.vickikbt.cache.di.cacheModule
import com.vickikbt.notflix.di.presentationModule
import com.vickikbt.repository.di.repositoryModule
import com.vickikbt.shared.di.initKoin
import com.vickikbt.shared.domain.utils.NapierInit
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

@ExperimentalPagingApi
class NotflixApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = listOf(repositoryModule, presentationModule, cacheModule)
        initKoin {
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@NotflixApplication)
            modules(appModules)
        }

        if (BuildConfig.DEBUG) NapierInit().init()
    }
}
