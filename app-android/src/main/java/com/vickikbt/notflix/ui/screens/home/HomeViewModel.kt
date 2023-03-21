package com.vickikbt.notflix.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.utils.HomeUiStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiStates(isLoading = true))
    val homeUiState = _homeUiState.asStateFlow()

    init {
        fetchNowPlayingMovies()
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()
    }

    private fun fetchNowPlayingMovies() = viewModelScope.launch {
        moviesRepository.fetchNowPlayingMovies().collect { moviesResult ->
            moviesResult.onSuccess { movies ->
                _homeUiState.update { it.copy(nowPlayingMovies = movies, isLoading = false) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.localizedMessage, isLoading = false) }
            }
        }
    }

    private fun fetchTrendingMovies() = viewModelScope.launch {
        moviesRepository.fetchTrendingMovies().collect { moviesResult ->
            moviesResult.onSuccess { movies ->
                _homeUiState.update { it.copy(trendingMovies = movies, isLoading = false) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.localizedMessage, isLoading = false) }
            }
        }
    }

    private fun fetchPopularMovies() = viewModelScope.launch {
        moviesRepository.fetchPopularMovies().collect { moviesResult ->
            moviesResult.onSuccess { movies ->
                _homeUiState.update { it.copy(popularMovies = movies, isLoading = false) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.localizedMessage, isLoading = false) }
            }
        }
    }

    private fun fetchUpcomingMovies() = viewModelScope.launch {
        moviesRepository.fetchUpcomingMovies().collect { moviesResult ->
            moviesResult.onSuccess { movies ->
                _homeUiState.update { it.copy(upcomingMovies = movies, isLoading = false) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.localizedMessage, isLoading = false) }
            }
        }
    }
}
