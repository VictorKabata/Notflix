package com.vickikbt.repository.data_source

class FavoriteMovieRepositoryImpl constructor(
    private val apiService: com.vickikbt.network.ApiService,
    private val appDatabase: com.vickikbt.cache.AppDatabase
) : com.vickikbt.network.utils.SafeApiRequest() {

    /*override suspend fun saveFavoriteMovie(favoriteMovie: FavoriteMovie) {

    }

    override suspend fun getFavoriteMovies(): Flow<List<FavoriteMovie>> {
        //return appDatabase.favoritesDao().getFavoriteMovies()
    }*/
}