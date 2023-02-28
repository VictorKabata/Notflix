package com.vickikbt.shared.presentation.presenters

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.utils.Enums
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
        fetchNowPlayingMovies()
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()
    }

    private fun fetchNowPlayingMovies() {
        println("Fetching Now Playing Movies")
        val job = viewModelScope.launch(Dispatchers.Default) {
            try {
                moviesRepository.fetchMovies(category = Enums.MovieCategories.NOW_PLAYING.name)
                    .collect { _nowPlayingMovies.value = it }
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

    private fun fetchTrendingMovies() {
        println("Fetching Trending Movies")
        val job = viewModelScope.launch {
            try {
                moviesRepository.fetchMovies(Enums.MovieCategories.TRENDING.name)
                    .collect { _trendingMovies.value = it }
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
        println("Fetching Popular Movies")
        val job = viewModelScope.launch {
            try {
                moviesRepository.fetchMovies(category = Enums.MovieCategories.POPULAR.name)
                    .collect { _popularMovies.value = it }
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
        println("Fetching Upcoming Movies")
        val job = viewModelScope.launch {
            try {
                moviesRepository.fetchMovies(category = Enums.MovieCategories.UPCOMING.name)
                    .collect { _upcomingMovies.value = it }
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
}
