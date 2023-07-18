package ui.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.utils.HomeUiState
import com.vickikbt.shared.utils.isLoading
import com.vickikbt.shared.utils.onFailure
import com.vickikbt.shared.utils.onSuccess
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenModel constructor(private val moviesRepository: MoviesRepository) : ScreenModel {

    private val _homeUiState = MutableStateFlow(HomeUiState(isLoading = true))
    val homeUiState = _homeUiState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _homeUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    fun fetchNowPlayingMovies() = CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
        moviesRepository.fetchNowPlayingMovies().collect { moviesResult ->
            moviesResult.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _homeUiState.update { it.copy(nowPlayingMovies = movies) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.localizedMessage) }
            }
        }
    }

    fun fetchTrendingMovies() = CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
        moviesRepository.fetchTrendingMovies().collect { moviesResult ->
            moviesResult.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _homeUiState.update { it.copy(trendingMovies = movies) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.localizedMessage) }
            }
        }
    }

    fun fetchPopularMovies() = CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
        moviesRepository.fetchPopularMovies().collect { moviesResult ->
            moviesResult.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _homeUiState.update { it.copy(popularMovies = movies) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.localizedMessage) }
            }
        }
    }

    fun fetchUpcomingMovies() = CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
        moviesRepository.fetchUpcomingMovies().collect { moviesResult ->
            moviesResult.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _homeUiState.update { it.copy(upcomingMovies = movies) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.localizedMessage) }
            }
        }
    }
}
