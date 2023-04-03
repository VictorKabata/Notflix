package ui.home

class HomeScreenModelTest {

    /*@ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // subject under test
    private lateinit var homeScreenModel: HomeScreenModel

    // test helpers
    private val moviesRepository = mockk<MoviesRepository>()

    private val movies = mockk<List<Movie>>(relaxed = true)

    @Before
    fun setup() {
        homeScreenModel = HomeScreenModel(moviesRepository = moviesRepository)
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
            actual = homeScreenModel.homeUiState.value
        )

        homeScreenModel.fetchNowPlayingMovies()

        assertEquals(
            expected = HomeUiState(
                isLoading = false,
                nowPlayingMovies = movies,
                error = null
            ),
            actual = homeScreenModel.homeUiState.value
        )
    }

    @Test
    fun `fetchNowPlayingMovies returns error on failure`() = runTest {
        coEvery { moviesRepository.fetchNowPlayingMovies(page = any()) } throws Exception("Error")

        assertEquals(
            expected = HomeUiState(isLoading = true),
            actual = homeScreenModel.homeUiState.value
        )

        // when
        homeScreenModel.fetchNowPlayingMovies()

        // then
        assertEquals(
            expected = HomeUiState(
                isLoading = false,
                error = "Error",
                nowPlayingMovies = emptyList()
            ),
            actual = homeScreenModel.homeUiState.value
        )
    }*/
}
