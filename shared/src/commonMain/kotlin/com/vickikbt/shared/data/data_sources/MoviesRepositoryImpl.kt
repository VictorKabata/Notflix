package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.data.cache.sqldelight.daos.MovieDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.utils.Enums
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
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

    override suspend fun fetchMovies(category: String) {
        when (category) {
            Enums.MovieCategories.NOW_PLAYING.name -> {
                val networkResponse = apiService.fetchNowPlayingMovies().movies.take(5)
                Napier.e("Now playing network response: $networkResponse")
                moviesDao.saveMovies(movies = networkResponse.map { it.toEntity(category = category) })
            }
            Enums.MovieCategories.UPCOMING.name -> {
                val networkResponse = apiService.fetchUpcomingMovies().movies
                Napier.e("Upcoming network response: $networkResponse")
                moviesDao.saveMovies(movies = networkResponse.map { it.toEntity(category = category) })
            }
            Enums.MovieCategories.POPULAR.name -> {
                val networkResponse = apiService.fetchPopularMovies().movies
                moviesDao.saveMovies(movies = networkResponse.map { it.toEntity(category = category) })
            }
            else -> {
                val networkResponse =
                    apiService.fetchTrendingMovies().movies.filter { it.mediaType == "movie" }
                moviesDao.saveMovies(movies = networkResponse.map { it.toEntity(category = category) })
            }
        }
    }
}
