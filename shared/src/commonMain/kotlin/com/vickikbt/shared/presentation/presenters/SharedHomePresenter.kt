package com.vickikbt.shared.presentation.presenters

import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class SharedHomePresenter constructor(private val moviesRepository: MoviesRepository) :
    KoinComponent {

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
        /*fetchNowPlayingMovies()
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()*/
    }

    /*private fun fetchNowPlayingMovies() {
        val job = viewModelScope.launch(Dispatchers.Default) {
            moviesRepository.fetchNowPlayingMovies().collect { moviesResult ->
                moviesResult.onSuccess {
                    _nowPlayingMovies.value = it
                }.onFailure {
                    _error.value = it.message
                }
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value?.cancel()
            supervisorJob.value = null
        }
    }

    private fun fetchTrendingMovies() {
        val job = viewModelScope.launch {
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

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value?.cancel()
            supervisorJob.value = null
        }
    }

    private fun fetchPopularMovies() {
        val job = viewModelScope.launch {
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

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value?.cancel()
            supervisorJob.value = null
        }
    }

    private fun fetchUpcomingMovies() {
        val job = viewModelScope.launch {
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

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value?.cancel()
            supervisorJob.value = null
        }
    }*/
}
