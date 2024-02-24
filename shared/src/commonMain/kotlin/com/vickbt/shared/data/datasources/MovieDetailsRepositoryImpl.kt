package com.vickbt.shared.data.datasources

import com.vickbt.shared.domain.repositories.MovieDetailsRepository
import io.ktor.client.HttpClient

class MovieDetailsRepositoryImpl(
    private val httpClient: HttpClient,
    //private val favoriteMovieDao: FavoriteMovieDao
) : MovieDetailsRepository {

    /*override suspend fun fetchMovieDetails(movieId: Int): Flow<ResultState<MovieDetails>> {
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
