package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.data.cache.sqldelight.MovieEntity
import com.vickikbt.shared.data.cache.sqldelight.daos.MoviesDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
) : MoviesRepository {

    private suspend fun saveMovies(movieEntities: List<MovieEntity>) {
        /*Napier.e("Saving movies")
        movieEntities.forEach {
            Napier.e("Caching movie: ${it.title}")
            moviesDao.saveMovies(movieEntity = it)
        }*/
    }

    override suspend fun fetchNowPlayingMovies(category: String): Flow<List<Movie>?> {
        //val networkResponse = apiService.fetchNowPlayingMovies()?.movies?.map { it.toEntity(category = category) }

        //Napier.e("From network: $networkResponse")

        //networkResponse?.let { saveMovies(movieEntities = it) }

        val cacheResponse = moviesDao.getNowPlayingMovies
        return cacheResponse.map { it.map { movies -> movies.toDomain() } }
    }

    override suspend fun fetchMovies(category: String): Flow<List<Movie>?> {
        val isCategoryCacheAvailable = moviesDao.isCategoryCacheAvailable(category) > 0
        val networkResponse =
            apiService.fetchUpcomingMovies()?.movies?.map { it.toEntity(category = category) }

        // networkResponse?.let { saveMovies(movieEntities = it) }

        return flowOf(networkResponse?.map { it.toDomain() })
    }
}
