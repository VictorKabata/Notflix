package com.vickbt.shared.fakes

import com.vickbt.shared.domain.models.Movie
import com.vickbt.shared.domain.repositories.MoviesRepository
import com.vickbt.shared.utils.ResultState
import kotlinx.coroutines.flow.Flow

class FakeMoviesRepository:MoviesRepository {
    override suspend fun fetchNowPlayingMovies(page: Int): Flow<ResultState<List<Movie>?>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTrendingMovies(
        mediaType: String,
        timeWindow: String,
        page: Int
    ): Flow<ResultState<List<Movie>?>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchPopularMovies(page: Int): Flow<ResultState<List<Movie>?>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchUpcomingMovies(page: Int): Flow<ResultState<List<Movie>?>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovie(
        movieName: String,
        page: Int
    ): Flow<ResultState<List<Movie>?>> {
        TODO("Not yet implemented")
    }
}
