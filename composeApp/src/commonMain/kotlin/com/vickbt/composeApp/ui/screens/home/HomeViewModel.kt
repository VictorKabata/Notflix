package com.vickbt.composeApp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.composeApp.domain.repositories.MoviesRepository
import com.vickbt.composeApp.utils.HomeUiState
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
        moviesRepository.fetchNowPlayingMovies().onSuccess { data ->
            data.collectLatest { movies ->
                _homeUiState.update {
                    it.copy(
                        nowPlayingMovies = movies?.take(5), isLoading = false
                    )
                }
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchTrendingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchTrendingMovies().onSuccess { data ->
            data.collectLatest { movies ->
                _homeUiState.update { it.copy(trendingMovies = movies, isLoading = false) }
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchPopularMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchPopularMovies().onSuccess { data ->
            data.collectLatest { movies ->
                _homeUiState.update { it.copy(popularMovies = movies, isLoading = false) }
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchUpcomingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchUpcomingMovies().onSuccess { data ->
            data.collectLatest { movies ->
                _homeUiState.update { it.copy(upcomingMovies = movies, isLoading = false) }
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }
}
