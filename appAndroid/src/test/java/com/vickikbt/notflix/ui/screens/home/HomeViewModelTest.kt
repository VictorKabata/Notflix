package com.vickikbt.notflix.ui.screens.home

import org.robolectric.annotation.Config

// @RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class HomeViewModelTest {

    /* ToDo
    @get:Rule
    val rule = InstantTaskExecutorRule()

    // subject under test
    private lateinit var viewModel: HomeViewModel

    // test helpers
    private val moviesRepository = mockk<MoviesRepository>()

    private val movies = mockk<List<Movie>>(relaxed = true)

    @Before
    fun setup() {
        viewModel = HomeViewModel(moviesRepository = moviesRepository)
    }

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `fetchNowPlayingMovies returns data on success`() = runTest {
        coEvery { moviesRepository.fetchNowPlayingMovies(page = any()) } returns flowOf(
            NetworkResultState.Success(data = movies)
        )

        assertEquals(
            expected = HomeUiState(isLoading = true),
            actual = viewModel.homeUiState.value
        )

        viewModel.fetchNowPlayingMovies()

        assertEquals(
            expected = HomeUiState(
                isLoading = false,
                nowPlayingMovies = movies,
                error = null
            ),
            actual = viewModel.homeUiState.value
        )
    }

    @Test
    fun `fetchNowPlayingMovies returns error on failure`() = runTest {
        coEvery { moviesRepository.fetchNowPlayingMovies(page = any()) } throws Exception("Error")

        assertEquals(
            expected = HomeUiState(isLoading = true),
            actual = viewModel.homeUiState.value
        )

        // when
        viewModel.fetchNowPlayingMovies()

        // then
        assertEquals(
            expected = HomeUiState(
                isLoading = false,
                error = "Error",
                nowPlayingMovies = emptyList()
            ),
            actual = viewModel.homeUiState.value
        )
    }

    @Test
    fun `fetchTrendingMovies returns data on success`() = runTest {
        coEvery { moviesRepository.fetchTrendingMovies(page = any()) } returns flowOf(
            NetworkResultState.Success(data = movies)
        )

        assertEquals(
            expected = HomeUiState(isLoading = true),
            actual = viewModel.homeUiState.value
        )

        viewModel.fetchTrendingMovies()

        assertEquals(
            expected = HomeUiState(isLoading = false, trendingMovies = movies, error = null),
            actual = viewModel.homeUiState.value
        )
    }

    @Test
    fun `fetchTrendingMovies returns error on failure`() = runTest {
        coEvery { moviesRepository.fetchTrendingMovies(page = any()) } throws Exception("Error")

        assertEquals(
            expected = HomeUiState(isLoading = true),
            actual = viewModel.homeUiState.value
        )

        viewModel.fetchTrendingMovies()

        assertEquals(
            expected = HomeUiState(
                isLoading = false,
                error = "Error",
                trendingMovies = emptyList()
            ),
            actual = viewModel.homeUiState.value
        )
    }

    @Test
    fun `fetchPopularMovies returns data on success`() = runTest {
        coEvery { moviesRepository.fetchPopularMovies(page = any()) } returns flowOf(
            NetworkResultState.Success(data = movies)
        )

        assertEquals(
            expected = HomeUiState(isLoading = true),
            actual = viewModel.homeUiState.value
        )

        viewModel.fetchPopularMovies()

        // then
        assertEquals(
            expected = HomeUiState(
                isLoading = false,
                popularMovies = movies,
                error = null
            ),
            actual = viewModel.homeUiState.value
        )
    }

    @Test
    fun `fetchPopularMovies returns error on failure`() = runTest {
        coEvery { moviesRepository.fetchPopularMovies(page = any()) } throws Exception("Error")

        assertEquals(
            expected = HomeUiState(isLoading = true),
            actual = viewModel.homeUiState.value
        )

        viewModel.fetchPopularMovies()

        assertEquals(
            expected = HomeUiState(
                isLoading = false,
                error = "Error",
                popularMovies = emptyList()
            ),
            actual = viewModel.homeUiState.value
        )
    }

    @Test
    fun `fetchUpcomingMovies returns data on success`() = runTest {
        coEvery { moviesRepository.fetchUpcomingMovies(page = any()) } returns flowOf(
            NetworkResultState.Success(data = movies)
        )

        assertEquals(
            expected = HomeUiState(isLoading = true),
            actual = viewModel.homeUiState.value
        )

        viewModel.fetchUpcomingMovies()

        assertEquals(
            expected = HomeUiState(
                isLoading = false,
                upcomingMovies = movies,
                error = null
            ),
            actual = viewModel.homeUiState.value
        )
    }

    @Test
    fun `fetchUpcomingMovies returns error on failure`() = runTest {
        coEvery { moviesRepository.fetchUpcomingMovies(page = any()) } throws Exception("Error")

        assertEquals(
            expected = HomeUiState(isLoading = true),
            actual = viewModel.homeUiState.value
        )

        viewModel.fetchUpcomingMovies()

        assertEquals(
            expected = HomeUiState(
                isLoading = false,
                error = "Error",
                upcomingMovies = emptyList()
            ),
            actual = viewModel.homeUiState.value
        )
    }*/
}
