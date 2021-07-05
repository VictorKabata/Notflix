package com.vickikbt.network


import com.vickikbt.data.models.dto.*
import com.vickikbt.data.util.Constants.API_KEY
import com.vickikbt.data.util.Constants.STARTING_PAGE_INDEX
import com.vickikbt.network.models.CastDto
import com.vickikbt.network.models.PopularResultDto
import com.vickikbt.network.models.SimilarResultDto
import com.vickikbt.network.models.UpcomingResultDto
import com.vickikbt.network.models.VideoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("language") language: String = "en"
    ): Response<PopularResultDto>

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("language") language: String = "en"
    ): Response<UpcomingResultDto>

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
    ): Response<VideoDto>

    @GET("movie/{movie_id}/similar")
    suspend fun fetchSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("language") language: String = "en"
    ): Response<SimilarResultDto>


}