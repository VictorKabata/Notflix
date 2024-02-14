package com.vickbt.shared.ui.screens.home

import com.vickbt.shared.domain.repositories.MoviesRepository
import com.vickbt.shared.fakes.FakeMovieData
import com.vickbt.shared.fakes.FakeMoviesRepository
import com.vickbt.shared.utils.ResultState
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.mock
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class HomeViewModelTest {

    // Subject under test
    private lateinit var homeViewModel: HomeViewModel

    // Test helpers
    @Mock
    private val moviesRepository = mock(classOf<MoviesRepository>())
    // private val moviesRepository:MoviesRepository=FakeMoviesRepository()
    private val customException = Exception("Error message!")

    @BeforeTest
    fun setup() {
        homeViewModel = HomeViewModel(moviesRepository = moviesRepository)
    }

    @Test
    fun fetchNowPlayingMovies_updates_homeUiState_now_playing_data_on_success() = runTest {
        // When
        homeViewModel.fetchNowPlayingMovies()

        // Given
        coEvery { moviesRepository.fetchNowPlayingMovies() }.returns(flowOf(ResultState.Success(data = FakeMovieData.movies)))

        // Then
        assertEquals(
            expected = FakeMovieData.movies,
            actual = homeViewModel.homeUiState.value.nowPlayingMovies
        )

    }

    @Test
    fun fetchNowPlayingMovies_updates_homeUiState_error_on_failure() = runTest {
        // Given
        coEvery { moviesRepository.fetchNowPlayingMovies() }.returns(
            flowOf(
                ResultState.Failure(exception = customException)
            )
        )

        // When
        homeViewModel.fetchNowPlayingMovies()

        // Then
        assertNull(homeViewModel.homeUiState.value.nowPlayingMovies)
        assertEquals(expected = "Error message!", actual = homeViewModel.homeUiState.value.error)
    }

    @Test
    fun fetchTrendingMovies_update_homeUiState_trending_movies_data_on_success() = runTest {
        // Given
        coEvery { moviesRepository.fetchNowPlayingMovies() }.returns(flowOf(ResultState.Success(data = FakeMovieData.movies)))

        // When
        homeViewModel.fetchTrendingMovies()

        // Then
        assertEquals(
            expected = FakeMovieData.movies,
            actual = homeViewModel.homeUiState.value.trendingMovies
        )
    }

    @Test
    fun fetchTrendingMovies_update_homeUiState_error_on_failure() = runTest {
        // Given
        coEvery { moviesRepository.fetchTrendingMovies() }.returns(flowOf(ResultState.Success(data = FakeMovieData.movies)))

        // When
        homeViewModel.fetchTrendingMovies()

        // Then
        assertEquals(
            expected = FakeMovieData.movies,
            actual = homeViewModel.homeUiState.value.trendingMovies
        )
    }

    @Test
    fun fetchUpcomingMovies_update_homeUiState_upcoming_movies_data_on_success() = runTest {
        // Given
        coEvery { moviesRepository.fetchUpcomingMovies() }.returns(flowOf(ResultState.Success(data = FakeMovieData.movies)))

        // When
        homeViewModel.fetchUpcomingMovies()

        // Then
        assertEquals(
            expected = FakeMovieData.movies,
            actual = homeViewModel.homeUiState.value.upcomingMovies
        )
    }

    @Test
    fun fetchUpcomingMovies_update_homeUiState_error_on_failure() = runTest {
        // Given
        coEvery { moviesRepository.fetchUpcomingMovies() }.returns(flowOf(ResultState.Success(data = FakeMovieData.movies)))

        // When
        homeViewModel.fetchUpcomingMovies()

        // Then
        assertEquals(
            expected = FakeMovieData.movies,
            actual = homeViewModel.homeUiState.value.upcomingMovies
        )
    }

    @Test
    fun fetchPopularMovies_update_homeUiState_popular_movies_data_on_success() = runTest {
        // Given
        coEvery { moviesRepository.fetchPopularMovies() }.returns(flowOf(ResultState.Success(data = FakeMovieData.movies)))

        // When
        homeViewModel.fetchPopularMovies()

        // Then
        assertEquals(
            expected = FakeMovieData.movies,
            actual = homeViewModel.homeUiState.value.popularMovies
        )
    }

    @Test
    fun fetchPopularMovies_update_homeUiState_error_on_failure() = runTest {
        // Given
        coEvery { moviesRepository.fetchPopularMovies() }.returns(flowOf(ResultState.Success(data = FakeMovieData.movies)))

        // When
        homeViewModel.fetchPopularMovies()

        // Then
        assertEquals(
            expected = FakeMovieData.movies,
            actual = homeViewModel.homeUiState.value.popularMovies
        )
    }

}
