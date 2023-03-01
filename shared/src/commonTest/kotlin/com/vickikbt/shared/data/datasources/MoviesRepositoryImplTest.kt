package com.vickikbt.shared.data.datasources

import com.vickikbt.shared.data.cache.sqldelight.daos.MovieDao
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.data.network.MockNotflixServer
import com.vickikbt.shared.domain.utils.Enums
import io.ktor.client.HttpClient
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest

class MoviesRepositoryImplTest {

    private val mockNotflixServer = MockNotflixServer()

    private lateinit var mockKtorHttpClient: HttpClient
    private lateinit var apiServiceTest: ApiService
    private lateinit var mockMoviesDao: MovieDao

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
    fun `now playing movies returns success on success`() = runTest {
        val response =
            moviesRepository.fetchMovies(category = Enums.MovieCategories.NOW_PLAYING).first()

        /*response.onSuccess {
            assertNotNull(it)
        }.onFailure {
            assertNull(it)
        }*/
        assertNotNull(response.getOrNull())
    }

    /*@Test
    fun `now playing movies returns error on failure`() = runTest {
        MockServer.expectSuccess(isSuccess = false)

        val response = moviesRepository.fetchMovies(category = Constants.CATEGORY_NOW_PLAYING_MOVIES).first()

        assertTrue(response is NetworkResult.Error)
    }*/

    /*@Test
    fun `upcoming movies returns success on success`() = runBlocking {
        val response =
            moviesRepository.fetchMovies(category = Constants.CATEGORY_UPCOMING_MOVIES).first()

        assertTrue(response is NetworkResult.Success)
    }*/

    /*@Test
    fun `upcoming movies returns error on failure`() = runTest {
        MockServer.expectSuccess(isSuccess = false)

        val response =
            moviesRepository.fetchMovies(category = Constants.CATEGORY_UPCOMING_MOVIES).first()

        assertTrue(response is NetworkResult.Error)
    }*/

    /*@Test
    fun `popular movies returns success on success`() = runTest {
        val response =
            moviesRepository.fetchMovies(category = Constants.CATEGORY_POPULAR_MOVIES).first()

        assertTrue(response is NetworkResult.Success)
    }*/

    /*@Test
    fun `popular movies returns error on failure`() = runTest {
        MockServer.expectSuccess(isSuccess = false)

        val response =
            moviesRepository.fetchMovies(category = Constants.CATEGORY_POPULAR_MOVIES).first()

        assertTrue(response is NetworkResult.Error)
    }*/

    /*@Test
    fun `trending movies returns success on success`() = runTest {
        val response =
            moviesRepository.fetchMovies(category = Constants.CATEGORY_TRENDING_MOVIES).first()

        assertTrue(response is NetworkResult.Success)
    }*/

    /*@Test
    fun `trending movies returns error on failure`() = runTest {
        MockServer.expectSuccess(isSuccess = false)

        val response =
            moviesRepository.fetchMovies(category = Constants.CATEGORY_TRENDING_MOVIES).first()

        assertTrue(response is NetworkResult.Error)
    }*/
}
