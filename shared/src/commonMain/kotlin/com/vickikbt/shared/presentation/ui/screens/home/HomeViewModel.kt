package com.vickikbt.shared.presentation.ui.screens.home

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
import org.koin.core.component.KoinComponent

class HomeViewModel constructor(private val moviesRepository: MoviesRepository) : KoinComponent {

    private val _homeUiState = MutableStateFlow(HomeUiState(isLoading = true))
    val homeUiState = _homeUiState.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _homeUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    fun fetchNowPlayingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchNowPlayingMovies().collect { moviesResult ->
            moviesResult.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _homeUiState.update { it.copy(nowPlayingMovies = movies) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.message) }
            }
        }
    }

    fun fetchTrendingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchTrendingMovies().collect { moviesResult ->
            moviesResult.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _homeUiState.update { it.copy(trendingMovies = movies) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.message) }
            }
        }
    }

    fun fetchPopularMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchPopularMovies().collect { moviesResult ->
            moviesResult.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _homeUiState.update { it.copy(popularMovies = movies) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.message) }
            }
        }
    }

    fun fetchUpcomingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchUpcomingMovies().collect { moviesResult ->
            moviesResult.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _homeUiState.update { it.copy(upcomingMovies = movies) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.message) }
            }
        }
    }
}
