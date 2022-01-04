package com.vickikbt.cache.di

import androidx.room.Room
import com.vickikbt.cache.datastore.TimeDatastore
import com.vickikbt.cache.preferences.ImagesPreferences
import com.vickikbt.cache.preferences.LanguagePreferences
import com.vickikbt.cache.preferences.PreferenceManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val cacheModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            com.vickikbt.cache.AppDatabase::class.java,
            "Notflix.db"
        ).fallbackToDestructiveMigration().build()
    }

    single { PreferenceManager(androidApplication()) }

    //ToDo: Remove other datastore instances
    single {
        TimeDatastore(androidApplication())
    }

    single {
        ThemePreferences(androidApplication())
    }

    single {
        LanguagePreferences(androidApplication())
    }

    single {
        ImagesPreferences(androidApplication())
    }
}