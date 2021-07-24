package com.vickikbt.repository.di

import com.vickikbt.domain.repository.MovieDetailsRepository
import com.vickikbt.domain.repository.PopularMoviesRepository
import com.vickikbt.domain.repository.SettingsRepository
import com.vickikbt.domain.repository.UpcomingMoviesRepository
import com.vickikbt.repository.data_source.MovieDetailsRepositoryImpl
import com.vickikbt.repository.data_source.PopularMoviesMoviesRepositoryImpl
import com.vickikbt.repository.data_source.SettingsRepositoryImpl
import com.vickikbt.repository.data_source.UpcomingMoviesMoviesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<MovieDetailsRepository> {
        MovieDetailsRepositoryImpl(
            apiService = get(),
            appDatabase = get()
        )
    }

    single<PopularMoviesRepository> {
        PopularMoviesMoviesRepositoryImpl(
            apiService = get(),
            appDatabase = get(),
            timeDatastore = get()
        )
    }

    single<SettingsRepository> {
        SettingsRepositoryImpl(
            themePreferences = get()
        )
    }

    single<UpcomingMoviesRepository> {
        UpcomingMoviesMoviesRepositoryImpl(
            apiService = get(),
            appDatabase = get(),
            timeDatastore = get()
        )
    }


}