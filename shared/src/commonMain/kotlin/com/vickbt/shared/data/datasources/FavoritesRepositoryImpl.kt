package com.vickbt.shared.data.datasources

import com.vickbt.shared.FavoriteMovieEntity
import com.vickbt.shared.data.cache.sqldelight.daos.FavoriteMovieDao
import com.vickbt.shared.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class FavoritesRepositoryImpl(private val favoriteMovieDao: FavoriteMovieDao) :
    FavoritesRepository {

    override suspend fun getFavouriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return favoriteMovieDao.getAllFavoriteMovies()
    }

}
