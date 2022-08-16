package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.utils.Constants.CATEGORY_NOW_PLAYING_MOVIES
import com.vickikbt.shared.domain.utils.Constants.CATEGORY_POPULAR_MOVIES
import com.vickikbt.shared.domain.utils.Constants.CATEGORY_TRENDING_MOVIES
import com.vickikbt.shared.domain.utils.Constants.CATEGORY_UPCOMING_MOVIES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MoviesRepositoryImpl constructor(
    private val apiService: ApiService
) : MoviesRepository {

    override suspend fun fetchMovies(category: String): Flow<List<Movie>?> {
        val networkResponse = when (category) {
            CATEGORY_NOW_PLAYING_MOVIES -> {
                apiService.fetchNowPlayingMovies().movies?.map { it.toDomain(category = category) }
            }
            CATEGORY_UPCOMING_MOVIES -> {
                apiService.fetchUpcomingMovies()?.movies?.map { it.toDomain(category = category) }
            }
            CATEGORY_POPULAR_MOVIES -> {
                apiService.fetchPopularMovies()?.movies?.map { it.toDomain(category = category) }
            }
            CATEGORY_TRENDING_MOVIES -> {
                apiService.fetchTrendingMovies()?.movies?.filter { it.mediaType == "movie" }
                    ?.map { it.toDomain(category = category) }
            }
            else -> null
        }

        return flowOf(networkResponse)
    }
}
