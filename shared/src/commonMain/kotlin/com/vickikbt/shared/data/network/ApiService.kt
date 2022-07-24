package com.vickikbt.shared.data.network

import com.vickikbt.shared.data.network.models.CastDto
import com.vickikbt.shared.data.network.models.MovieDetailsDto
import com.vickikbt.shared.data.network.models.MovieResultsDto
import com.vickikbt.shared.data.network.models.MovieVideoDto
import com.vickikbt.shared.domain.utils.Constants.STARTING_PAGE_INDEX

interface ApiService {

    suspend fun fetchNowPlayingMovies(page: Int = STARTING_PAGE_INDEX): MovieResultsDto?

    suspend fun fetchPopularMovies(page: Int = STARTING_PAGE_INDEX): MovieResultsDto?

    suspend fun fetchTrendingMovies(
        mediaType: String = "movie",
        timeWindow: String = "week",
        page: Int = STARTING_PAGE_INDEX,
    ): MovieResultsDto?

    suspend fun fetchUpcomingMovies(page: Int = STARTING_PAGE_INDEX): MovieResultsDto?

    suspend fun fetchMovieDetails(movieId: Int): MovieDetailsDto?

    suspend fun fetchMovieCast(movieId: Int): CastDto?

    suspend fun fetchMovieVideos(movieId: Int): MovieVideoDto?

    suspend fun fetchSimilarMovies(movieId: Int, page: Int = STARTING_PAGE_INDEX): MovieResultsDto?
}
