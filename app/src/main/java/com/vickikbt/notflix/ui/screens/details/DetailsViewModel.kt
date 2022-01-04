package com.vickikbt.notflix.ui.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.domain.models.Cast
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.domain.models.MovieVideo
import com.vickikbt.domain.models.SimilarMovies
import com.vickikbt.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val movieDetailsRepository: MovieDetailsRepository
) : ViewModel() {
    private val mutableMovieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = mutableMovieDetails

    private val mutableMovieCast = MutableLiveData<Cast>()
    val movieCast: LiveData<Cast> = mutableMovieCast

    private val mutableMovieVideo = MutableLiveData<MovieVideo>()
    val movieVideo: LiveData<MovieVideo> = mutableMovieVideo

    private val mutableSimilarMovies = MutableLiveData<SimilarMovies>()
    val similarMovies: LiveData<SimilarMovies> = mutableSimilarMovies

    fun fetchMovieDetails(movieId: Int) = viewModelScope.launch {
        movieDetailsRepository.getMovieDetails(movieId).collect {
            mutableMovieDetails.value = it
        }
    }

    fun fetchMovieCast(movieId: Int) = viewModelScope.launch {
        movieDetailsRepository.getMovieCast(movieId).collect {
            mutableMovieCast.value = it
        }
    }

    fun fetchMovieVideo(movieId: Int) = viewModelScope.launch {
        movieDetailsRepository.getMovieVideos(movieId).collect {
            mutableMovieVideo.value = it
        }
    }

    fun fetchSimilarMovies(movieId: Int) = viewModelScope.launch {
        movieDetailsRepository.fetchSimilarMovies(movieId).collect {
            mutableSimilarMovies.value = it
        }
    }
}
