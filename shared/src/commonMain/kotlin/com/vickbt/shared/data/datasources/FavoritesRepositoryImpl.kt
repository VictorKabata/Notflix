package com.vickbt.shared.data.datasources

import com.vickbt.shared.data.cache.sqldelight.daos.FavoriteMovieDao
import com.vickbt.shared.data.mappers.toDomain
import com.vickbt.shared.domain.models.MovieDetails
import com.vickbt.shared.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(private val favoriteMovieDao: FavoriteMovieDao) :
    FavoritesRepository {

    override suspend fun getFavouriteMovies(): Flow<List<MovieDetails>> {
        return favoriteMovieDao.getAllFavoriteMovies()
            .map { it.map { movieDetail -> movieDetail.toDomain() } }
    }
}
