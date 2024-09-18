package com.vickbt.composeApp.data.datasources

import com.vickbt.composeApp.data.cache.room.RoomAppDatabase
import com.vickbt.composeApp.data.mappers.toDomain
import com.vickbt.composeApp.data.mappers.toEntity
import com.vickbt.composeApp.data.network.models.CastDto
import com.vickbt.composeApp.data.network.models.MovieDetailsDto
import com.vickbt.composeApp.data.network.models.MovieResultsDto
import com.vickbt.composeApp.data.network.utils.safeApiCall
import com.vickbt.composeApp.domain.models.Cast
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.domain.models.MovieDetails
import com.vickbt.composeApp.domain.repositories.MovieDetailsRepository
import com.vickbt.composeApp.utils.ResultState
import com.vickbt.composeApp.utils.toBoolean
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MovieDetailsRepositoryImpl(
    private val httpClient: HttpClient,
    private val appDatabase: RoomAppDatabase
) : MovieDetailsRepository {

    override suspend fun fetchMovieDetails(movieId: Int): Flow<ResultState<MovieDetails>> {
        val isMovieCached = isMovieFavorite(movieId = movieId)

        return if (isMovieCached == true) {
            try {
                val cachedFavoriteMovie = getFavoriteMovie(movieId = movieId)
                flowOf(ResultState.Success(data = cachedFavoriteMovie))
            } catch (e: Exception) {
                flowOf(ResultState.Failure(exception = e))
            }
        } else {
            flowOf(
                safeApiCall {
                    val response =
                        httpClient.get(urlString = "movie/$movieId").body<MovieDetailsDto>()
                    response.toDomain()
                }
            )
        }
    }

    override suspend fun fetchMovieCast(movieId: Int): Flow<ResultState<Cast>> {
        return flowOf(
            safeApiCall {
                val response = httpClient.get(urlString = "movie/$movieId/credits").body<CastDto>()

                response.toDomain()
            }
        )
    }

    override suspend fun fetchSimilarMovies(
        movieId: Int,
        page: Int
    ): Flow<ResultState<List<Movie>?>> {
        return flowOf(
            safeApiCall {
                val response = httpClient.get(urlString = "movie/$movieId/similar") {
                    parameter("page", page)
                }.body<MovieResultsDto>()

                response.movies?.map { it.toDomain() }
            }
        )
    }

    override suspend fun saveFavoriteMovie(movie: MovieDetails) {
        appDatabase.favoriteMovieDao().insertFavoriteMovie(favoriteMovie = movie.toEntity())
    }

    override suspend fun getFavoriteMovie(movieId: Int): MovieDetails {
        val favMovieDao = appDatabase.favoriteMovieDao()
        return favMovieDao.getFavoriteMovie(id = movieId)!!.toDomain()
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        appDatabase.favoriteMovieDao().deleteFavoriteMovie(id = movieId)
    }

    override suspend fun isMovieFavorite(movieId: Int): Boolean? {
        return appDatabase.favoriteMovieDao().isMovieFavorite(id = movieId).toBoolean()
    }
}
