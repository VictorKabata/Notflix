package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.data.network.utils.NetworkResult
import com.vickikbt.shared.data.network.utils.safeApiCall
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.utils.Constants.CATEGORY_NOW_PLAYING_MOVIES
import com.vickikbt.shared.domain.utils.Constants.CATEGORY_POPULAR_MOVIES
import com.vickikbt.shared.domain.utils.Constants.CATEGORY_UPCOMING_MOVIES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MoviesRepositoryImpl constructor(
    private val apiService: ApiService
) : MoviesRepository {

    override suspend fun fetchMovies(category: String): Flow<NetworkResult<List<Movie>>> {
        val networkResponse = when (category) {
            CATEGORY_NOW_PLAYING_MOVIES -> {
                safeApiCall {
                    apiService.fetchNowPlayingMovies().movies.map { it.toDomain(category = category) }
                }
            }
            CATEGORY_UPCOMING_MOVIES -> {
                safeApiCall {
                    apiService.fetchUpcomingMovies().movies.map { it.toDomain(category = category) }
                }
            }
            CATEGORY_POPULAR_MOVIES -> {
                safeApiCall {
                    apiService.fetchPopularMovies().movies.map { it.toDomain(category = category) }
                }
            }
            else -> {
                safeApiCall {
                    apiService.fetchTrendingMovies().movies
                        .filter { it.mediaType == "movie" }
                        .map { it.toDomain(category = category) }
                }
            }
        }

        return flowOf(networkResponse)
    }
}
