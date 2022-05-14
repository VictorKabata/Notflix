package com.vickikbt.repository.di

import androidx.paging.ExperimentalPagingApi
import com.vickikbt.repository.repository.favorites_repository.FavoriteMovieRepositoryImpl
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import com.vickikbt.repository.repository.movie_details_repository.MovieDetailsRepositoryImpl
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.repository.repository.movies_repository.MoviesRepositoryImpl
import org.koin.dsl.module

@ExperimentalPagingApi
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
