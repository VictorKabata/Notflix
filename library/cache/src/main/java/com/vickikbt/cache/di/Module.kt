package com.vickikbt.cache.di

import androidx.room.Room
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.datastore.ThemeDatastore
import com.vickikbt.cache.datastore.TimeDatastore
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val cacheModule = module {

    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "Notflix.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single{
        TimeDatastore(androidApplication())
    }

    single{
        ThemeDatastore(androidApplication())
    }
}