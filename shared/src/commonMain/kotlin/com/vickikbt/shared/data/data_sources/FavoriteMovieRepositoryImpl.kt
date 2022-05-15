package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FavoriteMovieRepositoryImpl : FavoritesRepository {

    override suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?> = flowOf(true)

    override suspend fun getFavoriteMovies(): Flow<List<Movie>> {
        return flowOf(emptyList<Movie>())
    }
}
