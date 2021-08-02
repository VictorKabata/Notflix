package com.vickikbt.repository.data_source

import com.vickikbt.cache.AppDatabase
import com.vickikbt.network.ApiService

class FavoriteMovieRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : com.vickikbt.network.utils.SafeApiRequest() {

    /*override suspend fun saveFavoriteMovie(favoriteMovie: FavoriteMovie) {

    }

    override suspend fun getFavoriteMovies(): Flow<List<FavoriteMovie>> {
        //return appDatabase.favoritesDao().getFavoriteMovies()
    }*/
}