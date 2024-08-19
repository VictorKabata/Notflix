package com.vickbt.shared.data.datasources

import com.vickbt.shared.data.cache.room.RoomAppDatabase
import com.vickbt.shared.data.mappers.toDomain
import com.vickbt.shared.domain.models.MovieDetails
import com.vickbt.shared.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val appDatabase: RoomAppDatabase
) : FavoritesRepository {

    override suspend fun getFavouriteMovies(): Flow<List<MovieDetails>> {
        return appDatabase.favoriteMovieDao().getAllFavoriteMovies().map {
            it.map { movieDetail -> movieDetail.toDomain() }
        }
    }
}
