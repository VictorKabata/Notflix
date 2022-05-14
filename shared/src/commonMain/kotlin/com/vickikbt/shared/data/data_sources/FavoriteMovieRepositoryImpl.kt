package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.data.cache.sqldelight.daos.MoviesDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteMovieRepositoryImpl constructor(
    private val moviesDao: MoviesDao
) : FavoritesRepository {

    override suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?> =
        moviesDao.isMovieFavorite(movieId)

    override suspend fun updateIsMovieFavorite(cacheId: Int, isFavorite: Boolean) =
        moviesDao.updateIsMovieFavorite(cacheId = cacheId, isFavourite = isFavorite)

    override suspend fun getFavoriteMovies(): Flow<List<Movie>> {
        return moviesDao.getFavouriteMovies.map { it.map { it.toDomain() } }
    }
}
