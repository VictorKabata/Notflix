package com.vickbt.composeApp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vickbt.composeApp.domain.repositories.MoviesRepository
import com.vickbt.composeApp.domain.repositories.TvShowsRepository
import com.vickbt.composeApp.utils.HomeUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesRepository: MoviesRepository,
    private val tvShowsRepository: TvShowsRepository
) : ViewModel() {

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

        fetchAiringTodayTvShows()
        fetchTrendingTvShows()
        fetchTopRatedTvShows()
        fetchPopularTvShows()
    }

    fun fetchNowPlayingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchNowPlayingMovies().onSuccess { data ->
            data.collectLatest { movies ->
                _homeUiState.update {
                    it.copy(
                        nowPlayingMovies = movies?.take(5),
                        isLoading = false
                    )
                }
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchTrendingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchTrendingMovies().onSuccess { data ->
            _homeUiState.update {
                it.copy(
                    trendingMovies = data.cachedIn(viewModelScope),
                    isLoading = false
                )
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchPopularMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchPopularMovies().onSuccess { data ->
            _homeUiState.update {
                it.copy(
                    popularMovies = data.cachedIn(viewModelScope),
                    isLoading = false
                )
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchUpcomingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchUpcomingMovies().onSuccess { data ->
            _homeUiState.update {
                it.copy(
                    upcomingMovies = data.cachedIn(viewModelScope),
                    isLoading = false
                )
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchAiringTodayTvShows() = viewModelScope.launch(coroutineExceptionHandler) {
        tvShowsRepository.fetchAiringTodayTvShows().onSuccess { data ->
            data.collectLatest { tvShows ->
                _homeUiState.update {
                    it.copy(airingTodayTvShows = tvShows?.take(5), isLoading = false)
                }
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchTrendingTvShows() = viewModelScope.launch(coroutineExceptionHandler) {
        tvShowsRepository.fetchTrendingTVShows().onSuccess { data ->
            _homeUiState.update {
                it.copy(
                    trendingTvShows = data.cachedIn(viewModelScope),
                    isLoading = false
                )
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchTopRatedTvShows() = viewModelScope.launch(coroutineExceptionHandler) {
        tvShowsRepository.fetchTopRatedTvShows().onSuccess { data ->
            _homeUiState.update {
                it.copy(
                    topRatedTvShows = data.cachedIn(viewModelScope),
                    isLoading = false
                )
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchPopularTvShows() = viewModelScope.launch(coroutineExceptionHandler) {
        tvShowsRepository.fetchPopularTvShows().onSuccess { data ->
            _homeUiState.update {
                it.copy(
                    popularTvShows = data.cachedIn(viewModelScope),
                    isLoading = false
                )
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }
}
