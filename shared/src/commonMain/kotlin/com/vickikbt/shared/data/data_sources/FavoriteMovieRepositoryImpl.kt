package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.data.cache.sqldelight.daos.MovieDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FavoriteMovieRepositoryImpl constructor(private val movieDao: MovieDao) :
    FavoritesRepository {

    override suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?> = flowOf(true)

    override suspend fun getFavouriteMovies(): Flow<List<MovieDetails>> {
        return movieDao.getFavouriteMovies().map {
            it.map { movieDetailsEntity -> movieDetailsEntity.toDomain() }
        }
    }

}
