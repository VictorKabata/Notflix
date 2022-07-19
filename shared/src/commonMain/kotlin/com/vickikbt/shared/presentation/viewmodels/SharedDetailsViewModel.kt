package com.vickikbt.shared.presentation.viewmodels

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.models.MovieVideo
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedDetailsViewModel constructor(private val movieDetailsRepository: MovieDetailsRepository) :
    KoinComponent {

    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val supervisorJob = MutableStateFlow<Job?>(null)

    //ToDo: Add UI State class

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails get() = _movieDetails.asStateFlow()

    private val _movieCast = MutableStateFlow<Cast?>(null)
    val movieCast get() = _movieCast.asStateFlow()

    private val _movieVideo = MutableStateFlow<MovieVideo?>(null)
    val movieVideo get() = _movieVideo.asStateFlow()

    private val _similarMovies = MutableStateFlow<List<Movie>?>(emptyList())
    val similarMovies get() = _similarMovies.asStateFlow()

    private val _movieIsFavorite = MutableStateFlow<Boolean>(false)
    val movieIsFavorite get() = _movieIsFavorite.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        _movieDetails.value=null
        Napier.e("Fetching movie details")

        val job = viewModelScope.launch {
            movieDetailsRepository.getMovieDetails(movieId).collectLatest {
                _movieDetails.value = it
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    fun getMovieCast(movieId: Int) {
        _movieCast.value=null

        val job = viewModelScope.launch {
            movieDetailsRepository.getMovieCast(movieId).collectLatest {
                _movieCast.value = it
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    /*fun getMovieVideo(movieId: Int) {
        val job = viewModelScope.launch {
            movieDetailsRepository.getMovieVideos(movieId).collectLatest {
                _movieVideo.value = it
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value?.cancel()
            supervisorJob.value = null
        }
    }*/

    fun fetchSimilarMovies(movieId: Int) {
        _similarMovies.value=null

        val job = viewModelScope.launch {
            movieDetailsRepository.fetchSimilarMovies(movieId).collectLatest {
                _similarMovies.value = it
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    fun saveMovieDetails(movieDetails: MovieDetails, cast: Cast, movieVideo: MovieVideo?) {
        val job = viewModelScope.launch {
            movieDetailsRepository.apply {
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    fun updateFavorite(cacheId: Int, isFavorite: Boolean) {
        Napier.e("Updating : $cacheId to $isFavorite")

        val job = viewModelScope.launch {

        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    fun getIsMovieFavorite(movieId: Int) {
        val job = viewModelScope.launch {
            movieDetailsRepository.isMovieFavorite(movieId).collectLatest {
                _movieIsFavorite.value = it ?: false
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }
}
