package com.vickikbt.shared.data.datasources

import com.vickikbt.shared.data.cache.sqldelight.daos.MovieDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.utils.Enums
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class MoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val moviesDao: MovieDao
) : MoviesRepository {

    override suspend fun getMovies(category: String): Flow<List<Movie>> {
        val cachedResponse = moviesDao.getMoviesByCategory(category = category)
            .map { it.map { movieEntity -> movieEntity.toDomain(category = category) } }
            .onEach { movies ->
                if (movies.isEmpty()) fetchMovies(category = category)
            }

        return cachedResponse
    }

    override suspend fun fetchMovies(category: String): Flow<List<Movie>> {
        val networkResponse = when (category) {
            Enums.MovieCategories.NOW_PLAYING.name -> {
                apiService.fetchNowPlayingMovies().movies.take(5)
            }
            Enums.MovieCategories.UPCOMING.name -> {
                apiService.fetchUpcomingMovies().movies
            }
            Enums.MovieCategories.POPULAR.name -> {
                apiService.fetchPopularMovies().movies
            }
            else -> {
                apiService.fetchTrendingMovies().movies.filter { it.mediaType == "movie" }
            }
        }

        return flowOf(networkResponse.map { it.toDomain() })
        // moviesDao.saveMovies(movies = networkResponse.map { it.toEntity(category = category) })
    }
}
