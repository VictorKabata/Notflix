package com.vickikbt.repository.di

import com.vickikbt.repository.repository.favorites_repository.FavoriteMovieRepositoryImpl
import com.vickikbt.repository.repository.favorites_repository.FavoritesRepository
import com.vickikbt.repository.repository.movie_details_repository.MovieDetailsRepository
import com.vickikbt.repository.repository.movie_details_repository.MovieDetailsRepositoryImpl
import com.vickikbt.repository.repository.movies_repository.MoviesRepository
import com.vickikbt.repository.repository.movies_repository.MoviesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<MoviesRepository> {
        MoviesRepositoryImpl(
            apiService = get(),
            appDatabase = get()
        )
    }

    single<MovieDetailsRepository> {
        MovieDetailsRepositoryImpl(
            apiService = get(),
            appDatabase = get()
        )
    }

    single<FavoritesRepository> {
        FavoriteMovieRepositoryImpl(appDatabase = get())
    }


}