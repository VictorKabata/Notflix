package com.vickikbt.shared.presentation.viewmodels

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedHomeViewModel constructor(private val moviesRepository: MoviesRepository) :
    KoinComponent {

    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)

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
        try {
            moviesRepository.fetchMovies(category = Constants.CATEGORY_NOW_PLAYING_MOVIES)
                .collectLatest {
                    _nowPlayingMovies.value = it
                }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }

    private fun fetchTrendingMovies() = viewModelScope.launch {
        viewModelScope.launch {
            try {
                moviesRepository.fetchMovies(category = Constants.CATEGORY_TRENDING_MOVIES)
                    .collectLatest {
                        _trendingMovies.value = it
                    }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    private fun fetchPopularMovies() = viewModelScope.launch {
        viewModelScope.launch {
            try {
                moviesRepository.fetchMovies(category = Constants.CATEGORY_POPULAR_MOVIES)
                    .collectLatest {
                        _popularMovies.value = it
                    }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    private fun fetchUpcomingMovies() = viewModelScope.launch {
        viewModelScope.launch {
            try {
                moviesRepository.fetchMovies(category = Constants.CATEGORY_UPCOMING_MOVIES)
                    .collectLatest {
                        _upcomingMovies.value = it
                    }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
