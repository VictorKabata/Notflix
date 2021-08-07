package com.company.home.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.repository.NowPlayingMoviesRepository
import com.vickikbt.domain.repository.PopularMoviesRepository
import com.vickikbt.domain.repository.TrendingMoviesRepository
import com.vickikbt.domain.repository.UpcomingMoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val nowPlayingMoviesRepository: NowPlayingMoviesRepository,
    private val popularMoviesRepository: PopularMoviesRepository,
    private val trendingMoviesRepository: TrendingMoviesRepository,
    private val upcomingMoviesMoviesRepository: UpcomingMoviesRepository
) : ViewModel() {

    private val _nowPlayingMovies = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val nowPlayingMovies: StateFlow<HomeUiState> = _nowPlayingMovies

    private val _trendingMovies = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val trendingMovies: StateFlow<HomeUiState> = _trendingMovies

    private val _popularMovies = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val popularMovies: StateFlow<HomeUiState> = _popularMovies

    private val _upcomingMovies = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val upcomingMovies: StateFlow<HomeUiState> = _upcomingMovies

    init {
        fetchNowPlayingMovies()
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()
    }

    private fun fetchNowPlayingMovies() = viewModelScope.launch {
        try {
            val nowPlayingMoviesResponse = nowPlayingMoviesRepository.fetchNowPlayingMovies()

            nowPlayingMoviesResponse.collect { result ->
                _nowPlayingMovies.value = HomeUiState.Success(result)
            }
        } catch (e: Exception) {
            _nowPlayingMovies.value = HomeUiState.Error("${e.message}")
        }
    }

    private fun fetchTrendingMovies() = viewModelScope.launch {
        try {
            val trendingMoviesResponse = trendingMoviesRepository.fetchTrendingMovies()

            trendingMoviesResponse.collect { result ->
                _trendingMovies.value = HomeUiState.Success(result)
            }
        } catch (e: Exception) {
            _trendingMovies.value = HomeUiState.Error("${e.message}")
        }
    }

    private fun fetchPopularMovies() = viewModelScope.launch {
        try {
            val popularMoviesResponse = popularMoviesRepository.fetchPopularMovies()

            popularMoviesResponse.collect { result ->
                _popularMovies.value = HomeUiState.Success(result)
            }
        } catch (e: Exception) {
            _trendingMovies.value = HomeUiState.Error("${e.message}")
        }
    }

    private fun fetchUpcomingMovies() = viewModelScope.launch {
        try {
            val upcomingMoviesResponse = upcomingMoviesMoviesRepository.fetchUpcomingMovies()

            upcomingMoviesResponse.collect { result ->
                _upcomingMovies.value = HomeUiState.Success(result)
            }
        } catch (e: Exception) {
            _upcomingMovies.value = HomeUiState.Error("${e.message}")
        }
    }

    sealed class HomeUiState {
        object Loading : HomeUiState()
        data class Success(val movies: List<Movie>) : HomeUiState()
        data class Error(val error: String) : HomeUiState()
    }
}