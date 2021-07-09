package com.vickikbt.repository.di

import com.vickikbt.repository.repositories.movie_details.MovieDetailsRepository
import com.vickikbt.repository.repositories.movie_details.MovieDetailsRepositoryImpl
import com.vickikbt.repository.repositories.popular_movies.PopularMoviesMoviesRepositoryImpl
import com.vickikbt.repository.repositories.popular_movies.PopularMoviesRepository
import com.vickikbt.repository.repositories.settings.SettingsRepository
import com.vickikbt.repository.repositories.settings.SettingsRepositoryImpl
import com.vickikbt.repository.repositories.upcoming_movies.UpcomingMoviesRepositoryImpl
import com.vickikbt.repository.repositories.upcoming_movies.UpcomingRepository
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

    single<UpcomingRepository> {
        UpcomingMoviesRepositoryImpl(
            apiService = get(),
            appDatabase = get(),
            timeDatastore = get()
        )
    }


}