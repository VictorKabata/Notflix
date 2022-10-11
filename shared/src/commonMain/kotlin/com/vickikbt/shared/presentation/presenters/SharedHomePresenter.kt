package com.vickikbt.shared.presentation.presenters

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.vickikbt.shared.data.network.utils.NetworkResult
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedHomePresenter constructor(private val moviesRepository: MoviesRepository) :
    KoinComponent {

    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val supervisorJob = MutableStateFlow<Job?>(null)

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
        fetchMovies()
    }

    fun fetchMovies() {
        val job = viewModelScope.launch {
            fetchNowPlayingMovies()
            fetchPopularMovies()
            fetchUpcomingMovies()
            fetchTrendingMovies()
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value?.cancel()
            supervisorJob.value = null
        }
    }

    private suspend fun fetchNowPlayingMovies() {
        try {
            moviesRepository.fetchMovies(category = Constants.CATEGORY_NOW_PLAYING_MOVIES)
                .collectLatest {
                    when (it) {
                        is NetworkResult.Success -> _nowPlayingMovies.value = it.data
                        is NetworkResult.Error -> _error.value = it.errorMessage
                    }
                }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }

    private suspend fun fetchTrendingMovies() {
        try {
            moviesRepository.fetchMovies(category = Constants.CATEGORY_TRENDING_MOVIES)
                .collectLatest {
                    when (it) {
                        is NetworkResult.Success -> _trendingMovies.value = it.data
                        is NetworkResult.Error -> _error.value = it.errorMessage
                    }
                }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }

    private suspend fun fetchPopularMovies() {
        try {
            moviesRepository.fetchMovies(category = Constants.CATEGORY_POPULAR_MOVIES)
                .collectLatest {
                    when (it) {
                        is NetworkResult.Success -> _popularMovies.value = it.data
                        is NetworkResult.Error -> _error.value = it.errorMessage
                    }
                }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }

    private suspend fun fetchUpcomingMovies() {
        try {
            moviesRepository.fetchMovies(category = Constants.CATEGORY_UPCOMING_MOVIES)
                .collectLatest {
                    when (it) {
                        is NetworkResult.Success -> _upcomingMovies.value = it.data
                        is NetworkResult.Error -> _error.value = it.errorMessage
                    }
                }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }
}
