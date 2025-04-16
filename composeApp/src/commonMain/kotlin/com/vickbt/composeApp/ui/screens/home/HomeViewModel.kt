package com.vickbt.composeApp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vickbt.composeApp.domain.repositories.MoviesRepository
import com.vickbt.composeApp.domain.repositories.TvShowsRepository
import com.vickbt.composeApp.utils.HomeUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        fetchInitialData()
    }

    fun fetchInitialData() = viewModelScope.launch {
        _homeUiState.update { it.copy(isLoading = true) }
        try {
            withContext(Dispatchers.IO) {
                listOf(
                    async { fetchNowPlayingMovies() },
                    async { fetchTrendingMovies() },
                    async { fetchUpcomingMovies() },
                    async { fetchPopularMovies() },
                    async { fetchAiringTodayTvShows() },
                    async { fetchTrendingTvShows() },
                    async { fetchTopRatedTvShows() },
                    async { fetchPopularTvShows() }
                ).awaitAll()
            }
            _homeUiState.update { it.copy(isLoading = false) }
        } catch (e: Exception) {
            _homeUiState.update { it.copy(error = e.message, isLoading = false) }
        } finally {
            _homeUiState.update { it.copy(isLoading = false) }
        }
    }

    fun fetchNowPlayingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchNowPlayingMovies().onSuccess { data ->
            data.collectLatest { movies ->
                _homeUiState.update { it.copy(nowPlayingMovies = movies?.take(5)) }
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchTrendingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchTrendingMovies().onSuccess { data ->
            _homeUiState.update { it.copy(trendingMovies = data.cachedIn(viewModelScope)) }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchPopularMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchPopularMovies().onSuccess { data ->
            _homeUiState.update { it.copy(popularMovies = data.cachedIn(viewModelScope)) }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchUpcomingMovies() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchUpcomingMovies().onSuccess { data ->
            _homeUiState.update { it.copy(upcomingMovies = data.cachedIn(viewModelScope)) }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchAiringTodayTvShows() = viewModelScope.launch(coroutineExceptionHandler) {
        tvShowsRepository.fetchAiringTodayTvShows().onSuccess { data ->
            data.collectLatest { tvShows ->
                _homeUiState.update { it.copy(airingTodayTvShows = tvShows?.take(5)) }
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchTrendingTvShows() = viewModelScope.launch(coroutineExceptionHandler) {
        tvShowsRepository.fetchTrendingTVShows().onSuccess { data ->
            _homeUiState.update { it.copy(trendingTvShows = data.cachedIn(viewModelScope)) }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchTopRatedTvShows() = viewModelScope.launch(coroutineExceptionHandler) {
        tvShowsRepository.fetchTopRatedTvShows().onSuccess { data ->
            _homeUiState.update {
                it.copy(topRatedTvShows = data.cachedIn(viewModelScope))
            }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchPopularTvShows() = viewModelScope.launch(coroutineExceptionHandler) {
        tvShowsRepository.fetchPopularTvShows().onSuccess { data ->
            _homeUiState.update { it.copy(popularTvShows = data.cachedIn(viewModelScope)) }
        }.onFailure { error ->
            _homeUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }
}
