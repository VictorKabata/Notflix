package com.vickikbt.shared.data.datasources

import app.cash.turbine.test
import com.vickikbt.shared.data.network.MockNotflixServer
import com.vickikbt.shared.domain.models.ErrorResponse
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import com.vickikbt.shared.utils.onFailure
import com.vickikbt.shared.utils.onSuccess
import io.ktor.client.HttpClient
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
class MovieDetailsRepositoryImplTest {

    /*private val mockNotflixServer = MockNotflixServer()

    private lateinit var mockKtorHttpClient: HttpClient

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

        movieDetailsRepository = MovieDetailsRepositoryImpl(httpClient = mockKtorHttpClient)
    }

    @AfterTest
    fun tearDown() {
        mockKtorHttpClient.close()
    }

    @Test
    fun `fetchMovieDetails returns success on http 200`() = runTest {
        movieDetailsRepository.fetchMovieDetails(movieId = 1).test {
            this.awaitItem().onSuccess {
                assertNotNull(it)
            }.onFailure {
                assertNull(it)
            }

            awaitComplete()
        }
    }

    /*@Test
    fun `fetchMovieDetails returns failure on http 500`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        movieDetailsRepository.fetchMovieDetails(movieId = 1).test {
            val response = assertFailsWith<ErrorResponse> {
                this.awaitError()
            }

            assertEquals(actual = response, expected = errorResponse500)

            awaitComplete()
        }
    }*/

    @Test
    fun `fetchMovieCast returns success on http 200`() = runTest {
        movieDetailsRepository.fetchMovieCast(movieId = 1).test {
            this.awaitItem().onSuccess {
                assertNotNull(it)
            }.onFailure {
                assertNull(it)
            }

            awaitComplete()
        }
    }

    /*@Test
    fun `fetchMovieCast returns failure on http 500`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        movieDetailsRepository.fetchMovieCast(movieId = 1).test {
            val response = assertFailsWith<ErrorResponse> {
                this.awaitItem()
            }

            assertEquals(actual = response, expected = errorResponse500)

            awaitComplete()
        }
    }*/

    @Test
    fun `fetchSimilarMovies returns success on http 200`() = runTest {
        movieDetailsRepository.fetchSimilarMovies(movieId = 1).test {
            this.awaitItem().onSuccess {
                assertNotNull(it)
            }.onFailure {
                assertNull(it)
            }

            awaitComplete()
        }
    }

    @Test
    fun `fetchSimilarMovies returns failure on http 500`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        movieDetailsRepository.fetchSimilarMovies(movieId = 1).test {
            val response = assertFailsWith<ErrorResponse> {
                this.awaitItem()
            }

            assertEquals(
                expected = errorResponse500,
                actual = response
            )

            awaitComplete()
        }
    }*/
}
