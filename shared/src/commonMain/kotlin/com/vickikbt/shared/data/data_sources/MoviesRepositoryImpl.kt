package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MoviesRepositoryImpl constructor(
    private val apiService: ApiService
) : MoviesRepository {

    override suspend fun fetchNowPlayingMovies(category: String): Flow<List<Movie>?> {
        val networkResponse =
            apiService.fetchNowPlayingMovies()?.movies?.map { it.toDomain(category = category) }
        return flowOf(networkResponse)
    }

    override suspend fun fetchMovies(category: String): Flow<List<Movie>?> {
        val networkResponse =
            apiService.fetchUpcomingMovies()?.movies?.map { it.toDomain(category = category) }

        return flowOf(networkResponse)
    }
}
