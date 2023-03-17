package com.vickikbt.shared.data.datasources

import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.data.network.MockNotflixServer
import com.vickikbt.shared.domain.models.ErrorResponse
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
class MovieDetailsRepositoryImplTest {

    private val mockNotflixServer = MockNotflixServer()

    private lateinit var mockKtorHttpClient: HttpClient
    private lateinit var apiServiceTest: ApiService

    private val errorResponse500 = ErrorResponse(
        success = false,
        statusCode = 44,
        statusMessage = "The ID is invalid."
    )

    // Subject under test
    private lateinit var movieDetailsRepository: MovieDetailsRepository

    @BeforeTest
    fun setup() {
        mockKtorHttpClient = mockNotflixServer.mockHttpClient

        apiServiceTest = ApiService(httpClient = mockKtorHttpClient)

        movieDetailsRepository = MovieDetailsRepositoryImpl(apiService = apiServiceTest)
    }

    @AfterTest
    fun tearDown() {
        mockKtorHttpClient.close()
    }

    @Test
    fun `fetchMovieDetails returns success on http 200`() = runTest {
        val response = movieDetailsRepository.fetchMovieDetails(movieId = 1).first()

        response.onSuccess {
            assertNotNull(it)
        }.onFailure {
            assertNull(it)
        }
    }

    @Test
    fun `fetchMovieDetails returns failure on http 500`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        val response = assertFailsWith<ErrorResponse> {
            movieDetailsRepository.fetchMovieDetails(movieId = 1).first()
        }

        assertEquals(actual = response, expected = errorResponse500)
    }

    @Test
    fun `fetchMovieCast returns success on http 200`() = runTest {
        val response = movieDetailsRepository.fetchMovieCast(movieId = 1).first()

        response.onSuccess {
            assertNotNull(it)
        }.onFailure {
            assertNull(it)
        }
    }

    @Test
    fun `fetchMovieCast returns failure on http 500`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        val response = assertFailsWith<ErrorResponse> {
            movieDetailsRepository.fetchMovieCast(movieId = 1).first()
        }

        assertEquals(actual = response, expected = errorResponse500)
    }

    @Test
    fun `fetchSimilarMovies returns success on http 200`() = runTest {
        val response = movieDetailsRepository.fetchSimilarMovies(movieId = 1).first()

        response.onSuccess {
            assertNotNull(it)
        }.onFailure {
            assertNull(it)
        }
    }

    @Test
    fun `fetchSimilarMovies returns failure on http 500`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        val response = assertFailsWith<ErrorResponse> {
            movieDetailsRepository.fetchSimilarMovies(movieId = 1).first()
        }

        assertEquals(actual = response, expected = errorResponse500)
    }
}
