package com.vickikbt.shared.data.network

import com.vickikbt.shared.data.network.models.*
import com.vickikbt.shared.domain.utils.Constants.STARTING_PAGE_INDEX

interface ApiService {

    suspend fun fetchNowPlayingMovies(
        page: Int = STARTING_PAGE_INDEX,
        language: String = "en"
    ): MovieResultsDto?

    suspend fun fetchPopularMovies(
        page: Int = STARTING_PAGE_INDEX,
        language: String = "en"
    ): MovieResultsDto?

    suspend fun fetchTrendingMovies(
        mediaType: String = "movies",
        timeWindow: String = "week",
        page: Int = STARTING_PAGE_INDEX,
    ): MovieResultsDto?

    suspend fun fetchUpcomingMovies(
        page: Int = STARTING_PAGE_INDEX,
        language: String = "en"
    ): MovieResultsDto?

    suspend fun fetchMovieDetails(
        movieId: Int,
        language: String = "en"
    ): MovieDetailsDto?

    suspend fun fetchMovieCast(
        movieId: Int,
        language: String = "en"
    ): CastDto?

    suspend fun fetchMovieVideos(
        movieId: Int,
        language: String = "en"
    ): MovieVideoDto?

    suspend fun fetchSimilarMovies(
        movieId: Int,
        page: Int = STARTING_PAGE_INDEX,
        language: String = "en"
    ): SimilarMoviesDto?
}
