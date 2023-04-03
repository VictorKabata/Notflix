package com.vickikbt.shared.data.datasources

import app.cash.turbine.test
import com.vickikbt.shared.data.network.MockNotflixServer
import com.vickikbt.shared.domain.models.ErrorResponse
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
        moviesRepository.fetchNowPlayingMovies().test {
            this.awaitItem().onSuccess {
                assertNotNull(it)
            }.onFailure {
                assertNull(it)
            }

            awaitComplete()
        }
    }

    /*@Test
    fun `fetchNowPlayingMovies returns failure on http 500 error`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        moviesRepository.fetchNowPlayingMovies().test {
            val response = assertFailsWith<ErrorResponse> {
                this.awaitItem()
            }
            assertEquals(actual = response, expected = errorResponse500)
        }
    }*/

    @Test
    fun `fetchUpcomingMovies returns success on http 200`() = runTest {
        moviesRepository.fetchUpcomingMovies().test {
            this.awaitItem().onSuccess {
                assertNotNull(it)
            }.onFailure {
                assertNull(it)
            }

            awaitComplete()
        }
    }

    /*@Test
    fun `fetchUpcomingMovies returns failure on http 500 error`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        moviesRepository.fetchUpcomingMovies().test {

            val response = assertFailsWith<ErrorResponse> {
                this.awaitItem()
            }

            assertEquals(actual = response, expected = errorResponse500)
        }
    }*/

    @Test
    fun `fetchPopularMovies returns success on http 200`() = runTest {
        moviesRepository.fetchPopularMovies().test {
            this.awaitItem().onSuccess {
                assertNotNull(it)
            }.onFailure {
                assertNull(it)
            }

            awaitComplete()
        }
    }

    /*@Test
    fun `fetchPopularMovies returns failure on http 500 error`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        moviesRepository.fetchPopularMovies().test {

            val response = assertFailsWith<ErrorResponse> {
                this.awaitItem()
            }

            assertEquals(actual = response, expected = errorResponse500)
        }
    }*/

    @Test
    fun `fetchTrendingMovies returns success on http 200`() = runTest {
        moviesRepository.fetchTrendingMovies().test {
            this.awaitItem().onSuccess {
                assertNotNull(it)
            }.onFailure {
                assertNull(it)
            }

            awaitComplete()
        }
    }

    /*@Test
    fun `fetchTrendingMovies returns failure on http 500 error`() = runTest {
        mockNotflixServer.throwError(
            httpStatus = HttpStatusCode.InternalServerError,
            response = mockNotflixServer.mock500ErrorResponse
        )

        moviesRepository.fetchTrendingMovies().test {
            val response = assertFailsWith<ErrorResponse> {
                this.awaitItem()
            }

            assertEquals(actual = response, expected = errorResponse500)
        }
    }*/
}
