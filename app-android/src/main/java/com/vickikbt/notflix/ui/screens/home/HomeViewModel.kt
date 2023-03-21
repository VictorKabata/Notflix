package com.vickikbt.notflix.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.utils.HomeUiState
import com.vickikbt.shared.utils.NetworkResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        fetchNowPlayingMovies()
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()
    }

    private fun fetchNowPlayingMovies() = viewModelScope.launch {
        moviesRepository.fetchNowPlayingMovies().collect { moviesResult ->
            when (moviesResult) {
                is NetworkResultState.Loading -> {
                    _homeUiState.update { it.copy(isLoading = true) }
                }
                is NetworkResultState.Failure -> {
                    _homeUiState.update {
                        it.copy(
                            error = moviesResult.exception.localizedMessage,
                            isLoading = false
                        )
                    }
                }
                is NetworkResultState.Success -> {
                    _homeUiState.update { it.copy(nowPlayingMovies = moviesResult.data) }
                }
            }
        }
    }

    private fun fetchTrendingMovies() = viewModelScope.launch {
        moviesRepository.fetchTrendingMovies().collect { moviesResult ->
            when (moviesResult) {
                is NetworkResultState.Loading -> {
                    _homeUiState.update { it.copy(isLoading = true) }
                }
                is NetworkResultState.Failure -> {
                    _homeUiState.update {
                        it.copy(
                            error = moviesResult.exception.localizedMessage,
                            isLoading = false
                        )
                    }
                }
                is NetworkResultState.Success -> {
                    _homeUiState.update { it.copy(trendingMovies = moviesResult.data) }
                }
            }
        }
    }

    private fun fetchPopularMovies() = viewModelScope.launch {
        moviesRepository.fetchPopularMovies().collect { moviesResult ->
            when (moviesResult) {
                is NetworkResultState.Loading -> {
                    _homeUiState.update { it.copy(isLoading = true) }
                }
                is NetworkResultState.Failure -> {
                    _homeUiState.update {
                        it.copy(
                            error = moviesResult.exception.localizedMessage,
                            isLoading = false
                        )
                    }
                }
                is NetworkResultState.Success -> {
                    _homeUiState.update { it.copy(popularMovies = moviesResult.data) }
                }
            }
        }
    }

    private fun fetchUpcomingMovies() = viewModelScope.launch {
        moviesRepository.fetchUpcomingMovies().collect { moviesResult ->
            when (moviesResult) {
                is NetworkResultState.Loading -> {
                    _homeUiState.update { it.copy(isLoading = true) }
                }
                is NetworkResultState.Failure -> {
                    _homeUiState.update {
                        it.copy(
                            error = moviesResult.exception.localizedMessage,
                            isLoading = false
                        )
                    }
                }
                is NetworkResultState.Success -> {
                    _homeUiState.update { it.copy(upcomingMovies = moviesResult.data) }
                }
            }
        }
    }
}
