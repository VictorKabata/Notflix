package com.vickikbt.network


import com.vickikbt.domain.utils.Constants.API_KEY
import com.vickikbt.domain.utils.Constants.STARTING_PAGE_INDEX
import com.vickikbt.network.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("language") language: String = "en"
    ): Response<NowPlayingMoviesDto>

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("language") language: String = "en"
    ): Response<PopularMoviesDto>

    @GET("trending/{media_type}/{time_window}")
    suspend fun fetchTrendingMovies(
        @Path("media_type") mediaType: String = "movies",
        @Path("time_window") timeWindow: String = "week"
    ):Response<TrendingMoviesDto>

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("language") language: String = "en"
    ): Response<UpcomingMoviesDto>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): Response<MovieDetailsDto>

    @GET("movie/{movie_id}/credits")
    suspend fun fetchMovieCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): Response<CastDto>

    @GET("movie/{movie_id}/videos")
    suspend fun fetchMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): Response<MovieVideoDto>

    @GET("movie/{movie_id}/similar")
    suspend fun fetchSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("language") language: String = "en"
    ): Response<SimilarMoviesDto>


}