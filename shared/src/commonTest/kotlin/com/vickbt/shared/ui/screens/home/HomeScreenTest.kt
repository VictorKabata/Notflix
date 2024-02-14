package com.vickbt.shared.ui.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.runComposeUiTest
import com.vickbt.shared.domain.repositories.MoviesRepository
import com.vickbt.shared.fakes.FakeMovieData
import com.vickbt.shared.fakes.FakeMoviesRepository
import com.vickbt.shared.ui.screens.home.HomeViewModel
import com.vickbt.shared.ui.screens.home.HomeScreen
import com.vickbt.shared.ui.theme.NotflixTheme
import com.vickbt.shared.utils.HomeUiState
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.every
import io.mockative.mock
import moe.tlaster.precompose.navigation.Navigator
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class HomeScreenTest {

    private val moviesRepository = FakeMoviesRepository()

    private lateinit var homeViewModel: HomeViewModel

    @BeforeTest
    fun setup() {
        homeViewModel = HomeViewModel(moviesRepository = moviesRepository)
    }

    @Test
    fun progress_bar_is_displayed_when_homeUiState_is_in_loading_state() = runComposeUiTest {
        every { homeViewModel.homeUiState.value }.returns(HomeUiState(isLoading = true))

        setContent {
            NotflixTheme {
                HomeScreen(
                    navigator = Navigator(),
                    viewModel = homeViewModel,
                    paddingValues = PaddingValues()
                )
            }
        }

        onNodeWithTag("progress_bar_loading").assertIsDisplayed()
        onNodeWithTag("text_error_message").assertIsNotDisplayed()
        onNodeWithTag("column_home").assertIsNotDisplayed()

    }

    @Test
    fun progress_bar_is_not_displayed_when_homeUiState_is_not_in_loading_state() =
        runComposeUiTest {
            every { homeViewModel.homeUiState.value }.returns(HomeUiState(isLoading = false))

            setContent {
                NotflixTheme {
                    HomeScreen(
                        navigator = Navigator(),
                        viewModel = homeViewModel,
                        paddingValues = PaddingValues()
                    )
                }
            }

            onNodeWithTag("progress_bar_loading").assertIsNotDisplayed()
        }

    @Test
    fun error_message_is_displayed_when_homeUiState_has_error() = runComposeUiTest {
        every { homeViewModel.homeUiState.value }.returns(HomeUiState(error = "Error occurred!"))

        setContent {
            NotflixTheme {
                HomeScreen(
                    navigator = Navigator(),
                    viewModel = homeViewModel,
                    paddingValues = PaddingValues()
                )
            }
        }

        onNodeWithTag("text_error_message").assertIsDisplayed()
        onNodeWithTag("text_error_message").assertTextEquals("Error occurred!")
        onNodeWithTag("progress_bar_loading").assertIsNotDisplayed()
        onNodeWithTag("column_home").assertIsNotDisplayed()
    }

    @Test
    fun error_message_is_not_displayed_when_homeUiState_does_not_have_error() = runComposeUiTest {
        every { homeViewModel.homeUiState.value }.returns(HomeUiState(error = null))

        setContent {
            NotflixTheme {
                HomeScreen(
                    navigator = Navigator(),
                    viewModel = homeViewModel,
                    paddingValues = PaddingValues()
                )
            }
        }

        onNodeWithTag("text_error_message").assertIsNotDisplayed()
    }

    @Test
    fun now_playing_section_is_displayed_when_homeUiState_has_now_playing_movies_data() =
        runComposeUiTest {
            every { homeViewModel.homeUiState.value }.returns(
                HomeUiState(nowPlayingMovies = FakeMovieData.movies, isLoading = false)
            )

            setContent {
                NotflixTheme {
                    HomeScreen(
                        navigator = Navigator(),
                        viewModel = homeViewModel,
                        paddingValues = PaddingValues()
                    )
                }
            }

            onNodeWithTag("pager_now_playing").assertIsDisplayed()
        }

    @Test
    fun now_playing_section_is_not_displayed_when_homeUiState_has_now_playing_movies_is_null() =
        runComposeUiTest {
            every { homeViewModel.homeUiState.value }.returns(
                HomeUiState(nowPlayingMovies = null, isLoading = false)
            )

            setContent {
                NotflixTheme {
                    HomeScreen(
                        navigator = Navigator(),
                        viewModel = homeViewModel,
                        paddingValues = PaddingValues()
                    )
                }
            }

            onNodeWithTag("pager_now_playing").assertIsDisplayed()
        }

}
