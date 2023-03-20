package com.vickikbt.notflix.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _nowPlayingMovies = MutableStateFlow<List<Movie>?>(emptyList())
    val nowPlayingMovies get() = _nowPlayingMovies.asStateFlow()

    private val _trendingMovies = MutableStateFlow<List<Movie>?>(emptyList())
    val trendingMovies get() = _trendingMovies.asStateFlow()

    private val _popularMovies = MutableStateFlow<List<Movie>?>(emptyList())
    val popularMovies get() = _popularMovies.asStateFlow()

    private val _upcomingMovies = MutableStateFlow<List<Movie>?>(emptyList())
    val upcomingMovies get() = _upcomingMovies.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error get() = _error.asStateFlow()

    init {
        fetchNowPlayingMovies()
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()
    }

    private fun fetchNowPlayingMovies() = viewModelScope.launch {
        moviesRepository.fetchNowPlayingMovies().collect { moviesResult ->
            moviesResult.onSuccess {
                _nowPlayingMovies.value = it
            }.onFailure {
                _error.value = it.message
            }
        }
    }

    private fun fetchTrendingMovies() = viewModelScope.launch {
        try {
            moviesRepository.fetchTrendingMovies().collect { moviesResult ->
                moviesResult.onSuccess {
                    _trendingMovies.value = it
                }.onFailure {
                    _error.value = it.message
                }
            }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }

    private fun fetchPopularMovies() = viewModelScope.launch {
        try {
            moviesRepository.fetchPopularMovies().collect { moviesResult ->
                moviesResult.onSuccess {
                    _popularMovies.value = it
                }.onFailure {
                    _error.value = it.message
                }
            }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }

    private fun fetchUpcomingMovies() = viewModelScope.launch {
        try {
            moviesRepository.fetchUpcomingMovies().collect { moviesResult ->
                moviesResult.onSuccess {
                    _upcomingMovies.value = it
                }.onFailure {
                    _error.value = it.message
                }
            }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }
}
