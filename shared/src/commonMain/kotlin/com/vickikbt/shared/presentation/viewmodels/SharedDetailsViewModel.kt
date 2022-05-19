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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedDetailsViewModel constructor(private val movieDetailsRepository: MovieDetailsRepository) :
    KoinComponent {

    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val _movieDetails = MutableStateFlow<MovieDetails?>(MovieDetails())
    val movieDetails get() = _movieDetails

    private val _movieCast = MutableStateFlow<Cast?>(Cast())
    val movieCast get() = _movieCast

    private val _movieVideo = MutableStateFlow<MovieVideo?>(MovieVideo())
    val movieVideo get() = _movieVideo

    private val _similarMovies = MutableStateFlow<List<Movie>?>(emptyList())
    val similarMovies get() = _similarMovies

    private val _movieIsFavorite = MutableStateFlow<Boolean>(false)
    val movieIsFavorite get() = _movieIsFavorite

    fun getMovieDetails(movieId: Int) = viewModelScope.launch {
        Napier.e("Getting movie details")
        movieDetailsRepository.getMovieDetails(movieId).collectLatest {
            _movieDetails.value = it
        }
    }

    fun getMovieCast(movieId: Int) = viewModelScope.launch {
        movieDetailsRepository.getMovieCast(movieId).collectLatest {
            _movieCast.value = it
        }
    }

    fun getMovieVideo(movieId: Int) = viewModelScope.launch {
        movieDetailsRepository.getMovieVideos(movieId).collectLatest {
            _movieVideo.value = it
        }
    }

    fun fetchSimilarMovies(movieId: Int) = viewModelScope.launch {
        movieDetailsRepository.fetchSimilarMovies(movieId).collectLatest {
            _similarMovies.value = it
        }
    }

    fun saveMovieDetails(movieDetails: MovieDetails, cast: Cast, movieVideo: MovieVideo?) =
        viewModelScope.launch {
            movieDetailsRepository.apply {
            }
        }

    fun updateFavorite(cacheId: Int, isFavorite: Boolean) {
        Napier.e("Updating : $cacheId to $isFavorite")
        viewModelScope.launch {
        }
    }

    fun getIsMovieFavorite(movieId: Int) {
        viewModelScope.launch {
            movieDetailsRepository.isMovieFavorite(movieId).collectLatest {
                _movieIsFavorite.value = it ?: false
            }
        }
    }
}
