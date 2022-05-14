package com.vickikbt.repository.repository.favorites_repository

import com.vickikbt.cache.AppDatabase
import com.vickikbt.network.utils.SafeApiRequest
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.domain.repositories.FavoritesRepository
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

    override suspend fun getFavoriteMovies(): Flow<List<com.vickikbt.shared.domain.models.Movie>> {
        return movieDao.getFavoriteMovies().map { it.map { com.vickikbt.shared.data.mappers.toDomain() } }
    }
}
