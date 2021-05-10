package com.vickikbt.data.network

import com.google.common.truth.Truth.assertThat
import com.vickikbt.data.BaseTest
import com.vickikbt.data.util.SafeApiRequest
import com.vickikbt.data.utils.Constants.VALID_MOVIE_ID
import com.vickikbt.data.utils.RequestDispatcher
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


internal class ApiServiceTest:BaseTest() {

    /*private lateinit var server: MockWebServer

    private lateinit var apiService: ApiService

    private lateinit var safeApiRequest: SafeApiRequest

    @Before
    fun setUp() {
        server = MockWebServer()

        safeApiRequest = object : SafeApiRequest() {}

        server.dispatcher = RequestDispatcher()
        server.start()

        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        apiService = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)

        *//*val path = getJson("json/popular_movies_response.json")
        Timber.e("path in test:$path")

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(path)*//*

        //server.enqueue(response)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }*/

    @Test
    fun `fetch_popular_movies_test`() = runBlocking {
        val result =
            safeApiRequest.safeApiRequest { apiService.fetchPopularMovies("api_key", "language") }

        assertThat(result.movieDtos[0].id).isEqualTo(460465)
    }

    @Test
    fun `fetch_upcoming_movies_test`() = runBlocking {
        val result =
            safeApiRequest.safeApiRequest { apiService.fetchUpcomingMovies("api_key", "language") }

        assertThat(result.moviesDto[0].id).isEqualTo(460465)
    }

    @Test
    fun `fetch_movie_details_with_valid_movie_id_return_atleast_one_response`() = runBlocking {
        val result = safeApiRequest.safeApiRequest {
            apiService.fetchMovieDetails(
                VALID_MOVIE_ID,
                "api_key"
            )
        }

        assertThat(result.id).isEqualTo(VALID_MOVIE_ID)
    }

}