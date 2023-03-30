package com.vickikbt.shared.data.datasources

import com.vickikbt.shared.data.network.MockNotflixServer
import com.vickikbt.shared.domain.models.ErrorResponse
import com.vickikbt.shared.utils.onFailure
import com.vickikbt.shared.utils.onSuccess
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
class MoviesRepositoryImplTest {

    private val mockNotflixServer = MockNotflixServer()

    private lateinit var mockKtorHttpClient: HttpClient

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

        moviesRepository = MoviesRepositoryImpl(httpClient = mockKtorHttpClient)
    }

    @AfterTest
    fun tearDown() {
        mockKtorHttpClient.close()
    }

    @Test
    fun `fetchNowPlayingMovies returns success on http 200`() = runTest {
        val response = moviesRepository.fetchNowPlayingMovies().first()

        response.onSuccess {
            assertNotNull(it)
        }.onFailure {
            assertNull(it)
        }
    }

    @Test
    fun `fetchNowPlayingMovies returns failure on http 500 error`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        val response = assertFailsWith<ErrorResponse> {
            moviesRepository.fetchNowPlayingMovies().first()
        }

        assertEquals(actual = response, expected = errorResponse500)
    }

    @Test
    fun `fetchUpcomingMovies returns success on http 200`() = runTest {
        val response = moviesRepository.fetchUpcomingMovies().first()

        response.onSuccess {
            assertNotNull(it)
        }.onFailure {
            assertNull(it)
        }
    }

    @Test
    fun `fetchUpcomingMovies returns failure on http 500 error`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        val response = assertFailsWith<ErrorResponse> {
            moviesRepository.fetchUpcomingMovies().first()
        }

        assertEquals(actual = response, expected = errorResponse500)
    }

    @Test
    fun `fetchPopularMovies returns success on http 200`() = runTest {
        val response = moviesRepository.fetchPopularMovies().first()

        response.onSuccess {
            assertNotNull(it)
        }.onFailure {
            assertNull(it)
        }
    }

    @Test
    fun `fetchPopularMovies returns failure on http 500 error`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        val response = assertFailsWith<ErrorResponse> {
            moviesRepository.fetchPopularMovies().first()
        }

        assertEquals(actual = response, expected = errorResponse500)
    }

    @Test
    fun `fetchTrendingMovies returns success on http 200`() = runTest {
        val response = moviesRepository.fetchTrendingMovies().first()

        response.onSuccess {
            assertNotNull(it)
        }.onFailure {
            assertNull(it)
        }
    }

    @Test
    fun `fetchTrendingMovies returns failure on http 500 error`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        val response = assertFailsWith<ErrorResponse> {
            moviesRepository.fetchTrendingMovies().first()
        }

        assertEquals(actual = response, expected = errorResponse500)
    }
}
