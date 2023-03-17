package com.vickikbt.shared.data.datasources

import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.data.network.MockNotflixServer
import com.vickikbt.shared.domain.models.ErrorResponse
import com.vickikbt.shared.domain.utils.Enums
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
class MoviesRepositoryImplTest {

    private val mockNotflixServer = MockNotflixServer()

    private lateinit var mockKtorHttpClient: HttpClient
    private lateinit var apiServiceTest: ApiService

    private val errorResponse500 = ErrorResponse(
        success = false,
        statusCode = 44,
        statusMessage = "The ID is invalid."
    )

    // Subject under test
    private lateinit var moviesRepository: MoviesRepositoryImpl

    @BeforeTest
    fun setup() {
        mockKtorHttpClient = mockNotflixServer.mockHttpClient

        apiServiceTest = ApiService(httpClient = mockKtorHttpClient)

        moviesRepository = MoviesRepositoryImpl(apiService = apiServiceTest)
    }

    @AfterTest
    fun tearDown() {
        mockKtorHttpClient.close()
    }

    @Test
    fun `now playing movies returns success on http 200`() = runTest {
        val response =
            moviesRepository.fetchMovies(category = Enums.MovieCategories.NOW_PLAYING).first()

        assertNotNull(response.getOrNull())
        assertTrue(response.isSuccess)
        assertFalse(response.isFailure)
    }

    @Test
    fun `now playing movies returns failure on http 500 error`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        val response = assertFailsWith<ErrorResponse> {
            moviesRepository.fetchMovies(category = Enums.MovieCategories.NOW_PLAYING).first()
        }

        assertEquals(actual = response, expected = errorResponse500)
    }

    @Test
    fun `upcoming movies returns success on http 200`() = runTest {
        val response =
            moviesRepository.fetchMovies(category = Enums.MovieCategories.UPCOMING).first()

        assertNotNull(response.getOrNull())
        assertTrue(response.isSuccess)
        assertFalse(response.isFailure)
    }

    @Test
    fun `upcoming movies returns failure on http 500 error`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        val response = assertFailsWith<ErrorResponse> {
            moviesRepository.fetchMovies(category = Enums.MovieCategories.UPCOMING).first()
        }

        assertEquals(actual = response, expected = errorResponse500)
    }

    @Test
    fun `popular movies returns success on http 200`() = runTest {
        val response =
            moviesRepository.fetchMovies(category = Enums.MovieCategories.POPULAR).first()

        assertNotNull(response.getOrNull())
        assertTrue(response.isSuccess)
        assertFalse(response.isFailure)
    }

    @Test
    fun `popular movies returns failure on http 500 error`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        val response = assertFailsWith<ErrorResponse> {
            moviesRepository.fetchMovies(category = Enums.MovieCategories.POPULAR).first()
        }

        assertEquals(actual = response, expected = errorResponse500)
    }

    @Test
    fun `trending movies returns success on http 200`() = runTest {
        val response =
            moviesRepository.fetchMovies(category = Enums.MovieCategories.TRENDING).first()

        assertNotNull(response.getOrNull())
        assertTrue(response.isSuccess)
        assertFalse(response.isFailure)
    }

    @Test
    fun `trending movies returns failure on http 500 error`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        val response = assertFailsWith<ErrorResponse> {
            moviesRepository.fetchMovies(category = Enums.MovieCategories.TRENDING).first()
        }

        assertEquals(actual = response, expected = errorResponse500)
    }
}
