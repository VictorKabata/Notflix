package com.vickikbt.shared.data.datasources

import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.data.network.utils.safeApiCall
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.utils.Enums
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl constructor(
    private val apiService: ApiService
    // private val moviesDao: MovieDao
) : MoviesRepository {

    override suspend fun fetchMovies(category: Enums.MovieCategories): Flow<Result<List<Movie>>> =
        safeApiCall {
            val networkResponse = when (category) {
                Enums.MovieCategories.NOW_PLAYING -> {
                    apiService.fetchNowPlayingMovies().movies.take(5)
                }
                Enums.MovieCategories.UPCOMING -> {
                    apiService.fetchUpcomingMovies().movies
                }
                Enums.MovieCategories.POPULAR -> {
                    apiService.fetchPopularMovies().movies
                }
                else -> {
                    apiService.fetchTrendingMovies().movies.filter { it.mediaType == "movie" }
                }
            }

            return@safeApiCall networkResponse.map { it.toDomain() }
        }

    /*override suspend fun getMovies(category: String): Flow<List<Movie>> {
        val cachedResponse = moviesDao.getMoviesByCategory(category = category)
            .map { it.map { movieEntity -> movieEntity.toDomain(category = category) } }
            .onEach { movies ->
                // if (movies.isEmpty()) fetchMovies(category = category)
            }

        return cachedResponse
    }*/
}
