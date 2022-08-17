package com.vickikbt.shared.data.network

import com.vickikbt.shared.SampleData.Companion.MOCK_ERROR_RESPONSE
import com.vickikbt.shared.SampleData.Companion.MOCK_MOVIE_RESPONSE
import com.vickikbt.shared.data.network.MockServer.Companion.expectSuccess
import com.vickikbt.shared.data.network.MockServer.Companion.mockHttpClient
import com.vickikbt.shared.data.network.models.ApiError
import com.vickikbt.shared.data.network.models.MovieResultsDto
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.*

class ApiServiceTest {

    // Subject under test
    private val apiService = ApiService(httpClient = mockHttpClient)

    @BeforeTest
    fun setup() {

    }

    @AfterTest
    fun tearDown() {
        // mockHttpClient.close()
    }

    @Test
    fun `fetch now playing movies success returns movies`() = runBlocking {
        val networkResponse = apiService.fetchNowPlayingMovies()
        val expectedResponse = Json.decodeFromString<MovieResultsDto>(MOCK_MOVIE_RESPONSE)

        assertEquals(expected = expectedResponse, actual = networkResponse)
    }

}
