package com.vickbt.composeApp.data.datasources

import com.vickbt.composeApp.data.cache.AppDatabase
import com.vickbt.composeApp.data.mappers.toDomain
import com.vickbt.composeApp.domain.models.MovieDetails
import com.vickbt.composeApp.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val appDatabase: AppDatabase
) : FavoritesRepository {

    override suspend fun getFavouriteMovies(): Result<Flow<List<MovieDetails>?>> {
        return runCatching {
            appDatabase.favoriteMovieDao().getAllFavoriteMovies().map {
            it?.map { movieDetail -> movieDetail.toDomain() }
        }
        }
    }
}
