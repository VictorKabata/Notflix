package com.vickikbt.repository.data_source

import com.vickikbt.cache.AppDatabase
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.repository.FavoritesRepository
import com.vickikbt.network.utils.SafeApiRequest
import com.vickikbt.repository.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteMovieRepositoryImpl constructor(
    private val appDatabase: AppDatabase
) : FavoritesRepository, SafeApiRequest() {

    private val movieDao = appDatabase.moviesDao()

    override suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?> =
        movieDao.isMovieFavorite(movieId)

    override suspend fun updateIsMovieFavorite(cacheId: Int, isFavorite: Boolean) =
        movieDao.updateMovieIsFavorite(cacheId, isFavorite)

    override suspend fun getFavoriteMovies(): Flow<List<Movie>> {
        return movieDao.getFavoriteMovies().map { it.map { it.toDomain() } }
    }


}