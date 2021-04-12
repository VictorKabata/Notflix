package com.vickikbt.notflix.di

import android.app.Application
import com.vickikbt.data.datastore.TimeDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)

@Module
object DatastoreModule {

    @Provides
    @Singleton
    fun providesTimeDatastore(application: Application): TimeDatastore {
        return TimeDatastore(application)
    }
}