package com.vickikbt.shared.data.network

import com.vickikbt.shared.SampleData.Companion.MOCK_MOVIE_RESPONSE
import com.vickikbt.shared.data.network.MockServer.Companion.mockHttpClient
import com.vickikbt.shared.data.network.models.MovieResultsDto
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ApiServiceTest {

    // Subject under test
    private val apiService = ApiService(httpClient = mockHttpClient)

    @Test
    fun `fetch now playing movies success returns movies`() = runBlocking {
        val networkResponse = apiService.fetchNowPlayingMovies()
        val expectedResponse = Json.decodeFromString<MovieResultsDto>(MOCK_MOVIE_RESPONSE)

        assertNotNull(actual = networkResponse)
        assertEquals(expected = expectedResponse, actual = networkResponse)
    }

    @Test
    fun `fetch popular movies success returns movies`() = runBlocking {
        val networkResponse = apiService.fetchPopularMovies()
        val expectedResponse = Json.decodeFromString<MovieResultsDto>(MOCK_MOVIE_RESPONSE)

        assertNotNull(actual = networkResponse)
        assertEquals(expected = expectedResponse, actual = networkResponse)
    }

    @Test
    fun `fetch trending movies success returns movies`() = runBlocking {
        val networkResponse = apiService.fetchTrendingMovies()
        val expectedResponse = Json.decodeFromString<MovieResultsDto>(MOCK_MOVIE_RESPONSE)

        assertNotNull(actual = networkResponse)
        assertEquals(expected = expectedResponse, actual = networkResponse)
    }

    @Test
    fun `fetch upcoming movies success returns movies`() = runBlocking {
        val networkResponse = apiService.fetchUpcomingMovies()
        val expectedResponse = Json.decodeFromString<MovieResultsDto>(MOCK_MOVIE_RESPONSE)

        assertNotNull(actual = networkResponse)
        assertEquals(expected = expectedResponse, actual = networkResponse)
    }
}
