package com.vickikbt.shared.data.network

import com.vickikbt.shared.data.network.models.*
import com.vickikbt.shared.domain.utils.Constants.STARTING_PAGE_INDEX

interface ApiService {

    suspend fun fetchNowPlayingMovies(
        page: Int = STARTING_PAGE_INDEX,
        language: String = "en"
    ): NowPlayingMoviesDto?

    suspend fun fetchPopularMovies(
        page: Int = STARTING_PAGE_INDEX,
        language: String = "en"
    ): PopularMoviesDto?

    suspend fun fetchTrendingMovies(
        mediaType: String = "movies",
        timeWindow: String = "week",
        page: Int = STARTING_PAGE_INDEX,
    ): TrendingMoviesDto?

    suspend fun fetchUpcomingMovies(
        page: Int = STARTING_PAGE_INDEX,
        language: String = "en"
    ): UpcomingMoviesDto?

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
        page: Int = STARTING_PAGE_INDEX,
        language: String = "en"
    ): SimilarMoviesDto?
}
