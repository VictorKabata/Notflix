package com.vickikbt.shared.presentation.presenters

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.models.MovieVideo
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedDetailsPresenter constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val favouritesPresenter: FavoritesRepository
) : KoinComponent {

    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val supervisorJob = MutableStateFlow<Job?>(null)

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

    private val _error = MutableStateFlow<String?>(null)
    val error get() = _error.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        _movieDetails.value = null

        val job = viewModelScope.launch {
            movieDetailsRepository.fetchMovieDetails(movieId = movieId)
                .collect { movieDetailsResult ->
                    movieDetailsResult.onSuccess {
                        _movieDetails.value = it
                    }.onFailure {
                        _error.value = it.message
                    }
                }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    fun getMovieCast(movieId: Int) {
        _movieCast.value = null

        val job = viewModelScope.launch {
            movieDetailsRepository.fetchMovieCast(movieId = movieId).collect { movieCastsResult ->
                movieCastsResult.onSuccess {
                    _movieCast.value = it
                }.onFailure {
                    _error.value = it.message
                }
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    fun fetchSimilarMovies(movieId: Int) {
        _similarMovies.value = null

        val job = viewModelScope.launch {
            movieDetailsRepository.fetchSimilarMovies(movieId).collect { moviesResult ->
                moviesResult.onSuccess {
                    _similarMovies.value = it
                }.onFailure {
                    _error.value = it.message
                }
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    @Deprecated("Pending caching implementation")
    fun saveMovieDetails(movieDetails: MovieDetails) {
        val job = viewModelScope.launch {
            try {
                movieDetailsRepository.apply {
                    movieDetailsRepository.saveMovieDetails(movieDetail = movieDetails)
                }
            } catch (e: Exception) {
                Napier.e("Error saving movie: $e")
            }
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    @Deprecated("Pending caching implementation")
    fun deleteFavouriteMovie(movieId: Int) {
        val job = viewModelScope.launch {
            favouritesPresenter.deleteFavouriteMovie(movieId = movieId)
        }

        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }
}
