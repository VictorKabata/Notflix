package com.vickikbt.notflix.utils

import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.utils.NetworkResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMoviesRepository : MoviesRepository {

    companion object {
        var isSuccess: Boolean = true

        val testMovie = listOf<Movie>(
            Movie(
                adult = false,
                backdropPath = "",
                id = 1,
                originalLanguage = "",
                originalTitle = "",
                overview = "",
                popularity = 4.0,
                posterPath = "",
                releaseDate = "",
                title = "",
                video = false,
                voteAverage = 1.5,
                voteCount = 420,
                category = null,
                isFavorite = null,
                cacheId = null,
                mediaType = null
            )
        )
    }

    override suspend fun fetchNowPlayingMovies(page: Int): Flow<NetworkResultState<List<Movie>?>> {
        return if (isSuccess) flowOf(NetworkResultState.Success(testMovie))
        else flowOf(NetworkResultState.Failure(Exception("Error")))
    }

    override suspend fun fetchTrendingMovies(
        mediaType: String,
        timeWindow: String,
        page: Int
    ): Flow<NetworkResultState<List<Movie>?>> {
        return if (isSuccess) flowOf(NetworkResultState.Success(testMovie))
        else flowOf(NetworkResultState.Failure(Exception("Error")))
    }

    override suspend fun fetchPopularMovies(page: Int): Flow<NetworkResultState<List<Movie>?>> {
        return if (isSuccess) flowOf(NetworkResultState.Success(testMovie))
        else flowOf(NetworkResultState.Failure(Exception("Error")))
    }

    override suspend fun fetchUpcomingMovies(page: Int): Flow<NetworkResultState<List<Movie>?>> {
        return if (isSuccess) flowOf(NetworkResultState.Success(testMovie))
        else flowOf(NetworkResultState.Failure(Exception("Error")))
    }
}
