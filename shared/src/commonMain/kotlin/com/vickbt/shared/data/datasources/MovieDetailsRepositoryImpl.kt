package com.vickbt.shared.data.datasources

import com.vickbt.shared.data.mappers.toDomain
import com.vickbt.shared.data.network.utils.safeApiCall
import com.vickbt.shared.domain.models.Movie
import com.vickbt.shared.domain.repositories.MovieDetailsRepository
import com.vickbt.shared.utils.ResultState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.victorkabata.models.MovieDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MovieDetailsRepositoryImpl(
    private val httpClient: HttpClient,
    // private val favoriteMovieDao: FavoriteMovieDao
) : MovieDetailsRepository {

    override suspend fun fetchMovieDetails(movieId: Int): Flow<ResultState<Movie>> {
        return flowOf(
            safeApiCall {
                val response =
                    httpClient.get(urlString = "movie") {
                        parameter("id", movieId)
                    }.body<MovieDto>()
                response.toDomain()
            }
        )
    }

    /*override suspend fun fetchMovieCast(movieId: Int): Flow<ResultState<Cast>> {
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
        favoriteMovieDao.saveFavoriteMovie(movie = movie)
    }

    override suspend fun getFavoriteMovie(movieId: Int): MovieDetails {
        return favoriteMovieDao.getFavoriteMovie(movieId = movieId).toDomain()
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        favoriteMovieDao.deleteFavouriteMovie(movieId = movieId)
    }

    override suspend fun isMovieFavorite(movieId: Int): Boolean? {
        return favoriteMovieDao.isMovieFavorite(movieId = movieId)?.toBoolean()
    }*/
}
