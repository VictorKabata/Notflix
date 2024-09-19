package com.vickbt.composeApp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.composeApp.domain.repositories.MoviesRepository
import com.vickbt.composeApp.utils.HomeUiState
import com.vickbt.composeApp.utils.isLoading
import com.vickbt.composeApp.utils.onFailure
import com.vickbt.composeApp.utils.onSuccess
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState(isLoading = true))
    val homeUiState = _homeUiState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _homeUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    init {
        fetchNowPlayingMovies()
        fetchTrendingMovies()
        fetchUpcomingMovies()
        fetchPopularMovies()
    }

    fun fetchNowPlayingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchNowPlayingMovies().collectLatest { moviesResult ->
            moviesResult.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _homeUiState.update { it.copy(nowPlayingMovies = movies?.take(5)) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.message) }
            }
        }
    }

    fun fetchTrendingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchTrendingMovies().collectLatest { moviesResult ->
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
        moviesRepository.fetchPopularMovies().collectLatest { moviesResult ->
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
        moviesRepository.fetchUpcomingMovies().collectLatest { moviesResult ->
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
