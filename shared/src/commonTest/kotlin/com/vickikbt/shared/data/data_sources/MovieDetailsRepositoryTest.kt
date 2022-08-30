package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.data.network.MockServer
import com.vickikbt.shared.data.network.models.MovieDetailsDto
import com.vickikbt.shared.data.network.utils.NetworkResult
import com.vickikbt.shared.domain.utils.Constants
import io.ktor.client.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.*

class MovieDetailsRepositoryTest {

    private lateinit var mockHttpClient: HttpClient
    private lateinit var apiServiceTest: ApiService

    // Subject under test
    private lateinit var movieDetailsRepository: MovieDetailsRepositoryImpl

    @BeforeTest
    fun setup() {
        mockHttpClient = MockServer.mockHttpClient

        apiServiceTest = ApiService(httpClient = mockHttpClient)

        movieDetailsRepository = MovieDetailsRepositoryImpl(apiService = apiServiceTest)
    }

    @AfterTest
    fun tearDown() {
        mockHttpClient.close()
    }

    @Test
    fun `movie details returns success on success`() = runBlocking {
        val response = movieDetailsRepository.getMovieDetails(movieId = 1).first()
        // val expected = Json.decodeFromString<MovieDetailsDto>(MockServer.mockMovieDetailsResponse)

        assertTrue(response != null)
        // assertEquals(expected = expected.toDomain(), actual = response)
    }

    /*@Test
    fun `upcoming movies returns success on success`() = runBlocking {
        MockServer.expectSuccess(isSuccess = true)

        val response =
            moviesRepository.fetchMovies(category = Constants.CATEGORY_UPCOMING_MOVIES).first()

        assertTrue(response is NetworkResult.Success)
    }

    @Test
    fun `upcoming movies returns error on failure`() = runBlocking {
        MockServer.expectSuccess(isSuccess = false)

        val response =
            moviesRepository.fetchMovies(category = Constants.CATEGORY_UPCOMING_MOVIES).first()

        assertTrue(response is NetworkResult.Error)
    }

    @Test
    fun `popular movies returns success on success`() = runBlocking {
        MockServer.expectSuccess(isSuccess = true)

        val response =
            moviesRepository.fetchMovies(category = Constants.CATEGORY_POPULAR_MOVIES).first()

        assertTrue(response is NetworkResult.Success)
    }

    @Test
    fun `popular movies returns error on failure`() = runBlocking {
        MockServer.expectSuccess(isSuccess = false)

        val response =
            moviesRepository.fetchMovies(category = Constants.CATEGORY_POPULAR_MOVIES).first()

        assertTrue(response is NetworkResult.Error)
    }

    @Test
    fun `trending movies returns success on success`() = runBlocking {
        MockServer.expectSuccess(isSuccess = true)

        val response =
            moviesRepository.fetchMovies(category = Constants.CATEGORY_TRENDING_MOVIES).first()

        assertTrue(response is NetworkResult.Success)
    }

    @Test
    fun `trending movies returns error on failure`() = runBlocking {
        MockServer.expectSuccess(isSuccess = false)

        val response =
            moviesRepository.fetchMovies(category = Constants.CATEGORY_TRENDING_MOVIES).first()

        assertTrue(response is NetworkResult.Error)
    }*/

}
