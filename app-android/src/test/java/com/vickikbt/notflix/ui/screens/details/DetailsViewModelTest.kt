package com.vickikbt.notflix.ui.screens.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vickikbt.shared.domain.models.Actor
import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import com.vickikbt.shared.utils.DetailsUiState
import com.vickikbt.shared.utils.NetworkResultState
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class DetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    // subject under test
    private lateinit var viewModel: DetailsViewModel

    // test helpers
    private val movieDetailsRepository = mockk<MovieDetailsRepository>()

    private val movieDetails = mockk<MovieDetails>(relaxed = true)
    private val movieCast = mockk<Cast>(relaxed = true)
    private val similarMovies = mockk<List<Movie>>(relaxed = true)

    @Before
    fun setup() {
        viewModel = DetailsViewModel(movieDetailsRepository = movieDetailsRepository)
    }

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `getMovieDetails returns data on success`() = runTest {
        coEvery { movieDetailsRepository.fetchMovieDetails(movieId = any()) } returns flowOf(
            NetworkResultState.Success(data = movieDetails)
        )

        assertEquals(
            expected = DetailsUiState(isLoading = true),
            actual = viewModel.movieDetailsState.value
        )

        viewModel.getMovieDetails(movieId = 1)

        assertEquals(
            expected = DetailsUiState(isLoading = false, error = null, movieDetails = movieDetails),
            actual = viewModel.movieDetailsState.value
        )
    }

    @Test
    fun `getMovieDetails returns error on failure`() = runTest {
        coEvery { movieDetailsRepository.fetchMovieDetails(movieId = any()) } throws Exception("Error")

        assertEquals(
            expected = DetailsUiState(isLoading = true),
            actual = viewModel.movieDetailsState.value
        )

        viewModel.getMovieDetails(movieId = 1)

        assertEquals(
            expected = DetailsUiState(isLoading = false, error = "Error"),
            actual = viewModel.movieDetailsState.value
        )
    }

    @Test
    fun `getMovieCast returns data on success`() = runTest {
        coEvery { movieDetailsRepository.fetchMovieCast(movieId = any()) } returns flowOf(
            NetworkResultState.Success(data = movieCast)
        )

        assertEquals(
            expected = DetailsUiState(isLoading = true),
            actual = viewModel.movieDetailsState.value
        )

        viewModel.getMovieCast(movieId = 1)

        assertEquals(
            expected = DetailsUiState(isLoading = false, error = null, movieCast = movieCast.actor),
            actual = viewModel.movieDetailsState.value
        )
    }

    @Test
    fun `getMovieCast returns error on failure`() = runTest {
        coEvery { movieDetailsRepository.fetchMovieCast(movieId = any()) } throws Exception("Error")

        assertEquals(
            expected = DetailsUiState(isLoading = true),
            actual = viewModel.movieDetailsState.value
        )

        viewModel.getMovieCast(movieId = 1)

        assertEquals(
            expected = DetailsUiState(isLoading = false, error = "Error"),
            actual = viewModel.movieDetailsState.value
        )
    }

    @Test
    fun `fetchSimilarMovies returns data on success`() = runTest {
        coEvery { movieDetailsRepository.fetchSimilarMovies(movieId = any()) } returns flowOf(
            NetworkResultState.Success(data = similarMovies)
        )

        assertEquals(
            expected = DetailsUiState(isLoading = true),
            actual = viewModel.movieDetailsState.value
        )

        viewModel.fetchSimilarMovies(movieId = 1)

        assertEquals(
            expected = DetailsUiState(
                isLoading = false,
                error = null,
                similarMovies = similarMovies
            ),
            actual = viewModel.movieDetailsState.value
        )
    }

    @Test
    fun `fetchSimilarMovies returns error on failure`() = runTest {
        coEvery { movieDetailsRepository.fetchSimilarMovies(movieId = any()) } throws Exception("Error")

        assertEquals(
            expected = DetailsUiState(isLoading = true),
            actual = viewModel.movieDetailsState.value
        )

        viewModel.fetchSimilarMovies(movieId = 1)

        assertEquals(
            expected = DetailsUiState(isLoading = false, error = "Error"),
            actual = viewModel.movieDetailsState.value
        )
    }

}
