package com.vickikbt.notflix.ui.screens.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.vickikbt.notflix.utils.FakeMoviesRepository
import com.vickikbt.notflix.utils.FakeMoviesRepository.Companion.isSuccess
import com.vickikbt.notflix.utils.FakeMoviesRepository.Companion.testMovie
import com.vickikbt.shared.utils.HomeUiState
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    // subject under test
    private lateinit var viewModel: HomeViewModel

    // test helpers
    private val moviesRepository = FakeMoviesRepository()

    @Before
    fun setup() {
        viewModel = HomeViewModel(moviesRepository = moviesRepository)
    }

    @Test
    fun `fetchNowPlayingMovies returns data on success`() = runTest {
        //given
        isSuccess = true

        // when
        viewModel.fetchNowPlayingMovies()

        // then
        assertThat(viewModel.homeUiState.value).isEqualTo(
            HomeUiState(isLoading = false, nowPlayingMovies = testMovie, error = null)
        )
    }

    @Test
    fun `fetchNowPlayingMovies returns error on failure`() = runTest {
        //given
        isSuccess = false

        // when
        viewModel.fetchNowPlayingMovies()

        // then
        assertThat(viewModel.homeUiState.value).isEqualTo(
            HomeUiState(isLoading = false, error = "Error", nowPlayingMovies = emptyList())
        )
    }

    @Test
    fun `fetchTrendingMovies returns data on success`() = runTest {
        //given
        isSuccess = true

        // when
        viewModel.fetchTrendingMovies()

        // then
        assertThat(viewModel.homeUiState.value).isEqualTo(
            HomeUiState(isLoading = false, trendingMovies = testMovie, error = null)
        )
    }

    @Test
    fun `fetchTrendingMovies returns error on failure`() = runTest {
        //given
        isSuccess = false

        // when
        viewModel.fetchTrendingMovies()

        // then
        assertThat(viewModel.homeUiState.value).isEqualTo(
            HomeUiState(isLoading = false, error = "Error", trendingMovies = emptyList())
        )
    }

    @Test
    fun `fetchPopularMovies returns data on success`() = runTest {
        //given
        isSuccess = true

        // when
        viewModel.fetchPopularMovies()

        // then
        assertThat(viewModel.homeUiState.value).isEqualTo(
            HomeUiState(isLoading = false, popularMovies = testMovie, error = null)
        )
    }

    @Test
    fun `fetchPopularMovies returns error on failure`() = runTest {
        //given
        isSuccess = false

        // when
        viewModel.fetchPopularMovies()

        // then
        assertThat(viewModel.homeUiState.value).isEqualTo(
            HomeUiState(isLoading = false, error = "Error", popularMovies = emptyList())
        )
    }

    @Test
    fun `fetchUpcomingMovies returns data on success`() = runTest {
        //given
        isSuccess = true

        // when
        viewModel.fetchUpcomingMovies()

        // then
        assertThat(viewModel.homeUiState.value).isEqualTo(
            HomeUiState(isLoading = false, upcomingMovies = testMovie, error = null)
        )
    }

    @Test
    fun `fetchUpcomingMovies returns error on failure`() = runTest {
        //given
        isSuccess = false

        // when
        viewModel.fetchUpcomingMovies()

        // then
        assertThat(viewModel.homeUiState.value).isEqualTo(
            HomeUiState(isLoading = false, error = "Error", upcomingMovies = emptyList())
        )
    }


}
