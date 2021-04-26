package com.vickikbt.data.network


import com.vickikbt.data.models.dto.CastDto
import com.vickikbt.data.models.dto.MovieDetailsDto
import com.vickikbt.data.models.dto.PopularResultDto
import com.vickikbt.data.models.dto.UpcomingResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<PopularResultDto>

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<UpcomingResultDto>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<MovieDetailsDto>

    @GET("movie/{movie_id}/credits")
    suspend fun fetchMovieCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<CastDto>


}