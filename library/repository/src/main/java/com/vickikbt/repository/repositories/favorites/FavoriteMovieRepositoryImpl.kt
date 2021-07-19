package com.vickikbt.repository.repositories.favorites

import com.vickikbt.cache.AppDatabase
import com.vickikbt.network.ApiService
import com.vickikbt.repository.models.FavoriteMovie
import com.vickikbt.repository.utils.SafeApiRequest
import kotlinx.coroutines.flow.Flow

class FavoriteMovieRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : SafeApiRequest() {

    /*override suspend fun saveFavoriteMovie(favoriteMovie: FavoriteMovie) {

    }

    override suspend fun getFavoriteMovies(): Flow<List<FavoriteMovie>> {
        //return appDatabase.favoritesDao().getFavoriteMovies()
    }*/
}