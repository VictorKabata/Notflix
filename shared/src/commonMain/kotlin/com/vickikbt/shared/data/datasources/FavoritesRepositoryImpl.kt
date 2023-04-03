package com.vickikbt.shared.data.datasources

import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FavoritesRepositoryImpl constructor(private val httpClient: HttpClient) : FavoritesRepository {
    override suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?> {
        return flowOf(true)
    }

    override suspend fun getFavouriteMovies(): Flow<List<MovieDetails>> {
        return flowOf(emptyList())
    }

    override suspend fun deleteFavouriteMovie(movieId: Int) {
    }
}
