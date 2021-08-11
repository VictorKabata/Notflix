package com.company.details.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.domain.models.Cast
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.domain.models.MovieVideo
import com.vickikbt.domain.models.SimilarMovies
import com.vickikbt.domain.repository.FavoritesRepository
import com.vickikbt.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailsViewModel constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val movieDetails: StateFlow<MovieDetailsUiState> = _movieDetails

    private val _casts = MutableStateFlow<MovieCastUiState>(MovieCastUiState.Loading)
    val cast: StateFlow<MovieCastUiState> = _casts

    private val _videos = MutableStateFlow<MovieVideosUiState>(MovieVideosUiState.Loading)
    val videos: StateFlow<MovieVideosUiState> = _videos

    private val _similarMovies =
        MutableStateFlow<SimilarMoviesUiState>(SimilarMoviesUiState.Loading)
    val similarMovies: StateFlow<SimilarMoviesUiState> = _similarMovies

    private val _isMovieFavorite =
        MutableStateFlow<IsMovieFavoriteUiState>(IsMovieFavoriteUiState.Loading)
    val isMovieFavorite: StateFlow<IsMovieFavoriteUiState> = _isMovieFavorite

    //var stateListener: StateListener? = null

    //Get movieDetails
    fun getMovieDetails(movieId: Int) = viewModelScope.launch {
        try {
            val movieDetailsResponse = movieDetailsRepository.getMovieDetails(movieId)
            movieDetailsResponse.collect { movieDetails ->
                _movieDetails.value = MovieDetailsUiState.Success(movieDetails)

                isMovieFavorite(movieId = movieId)
                getMovieCast(movieId = movieId)
                getMovieVideos(movieId = movieId)
                getSimilarMovies(movieId = movieId)
            }
        } catch (e: Exception) {
            MovieDetailsUiState.Error("${e.message}")
        }
    }

    //Get movie cast
    private fun getMovieCast(movieId: Int) = viewModelScope.launch {
        try {
            val movieCastResponse = movieDetailsRepository.getMovieCast(movieId)
            movieCastResponse.collect { cast ->
                _casts.value = MovieCastUiState.Success(cast)
            }
        } catch (e: Exception) {
            _casts.value = MovieCastUiState.Error("${e.message}")
        }
    }

    //Get movie videos
    private fun getMovieVideos(movieId: Int) = viewModelScope.launch {
        try {
            val movieVideoResponse = movieDetailsRepository.getMovieVideos(movieId)
            movieVideoResponse.collect { video ->
                _videos.value = MovieVideosUiState.Success(video)
            }
        } catch (e: Exception) {
            _videos.value = MovieVideosUiState.Error("${e.message}")
        }
    }

    //Get similar movies
    private fun getSimilarMovies(movieId: Int) = viewModelScope.launch {
        try {
            val similarMoviesResponse = movieDetailsRepository.fetchSimilarMovies(movieId)
            similarMoviesResponse.collect { similarMovies ->
                _similarMovies.value = SimilarMoviesUiState.Success(similarMovies)
            }
        } catch (e: Exception) {
            _similarMovies.value = SimilarMoviesUiState.Error("${e.message}")
        }
    }

    private fun isMovieFavorite(movieId: Int) = viewModelScope.launch {
        val response = favoritesRepository.isMovieFavorite(movieId)
        response.collect {
            _isMovieFavorite.value = IsMovieFavoriteUiState.Success(it)
        }
    }

    fun updateIsMovieFavorite(cacheId: Int, isFavorite: Boolean) = viewModelScope.launch {
        try {
            favoritesRepository.updateIsMovieFavorite(cacheId, isFavorite)
        } catch (e: Exception) {
            Timber.e("Error updating isFav: ${e.message}")
        }
    }

    sealed class MovieDetailsUiState {
        object Loading : MovieDetailsUiState()
        data class Success(val movieDetails: MovieDetails) : MovieDetailsUiState()
        data class Error(val error: String) : MovieDetailsUiState()
    }

    sealed class MovieCastUiState {
        object Loading : MovieCastUiState()
        data class Success(val movieCast: Cast) : MovieCastUiState()
        data class Error(val error: String) : MovieCastUiState()
    }

    sealed class MovieVideosUiState {
        object Loading : MovieVideosUiState()
        data class Success(val videos: MovieVideo) : MovieVideosUiState()
        data class Error(val error: String) : MovieVideosUiState()
    }

    sealed class SimilarMoviesUiState {
        object Loading : SimilarMoviesUiState()
        data class Success(val similarMovies: SimilarMovies) : SimilarMoviesUiState()
        data class Error(val error: String) : SimilarMoviesUiState()
    }

    sealed class IsMovieFavoriteUiState {
        object Loading : IsMovieFavoriteUiState()
        data class Success(val isMovieFavorite: Boolean?) : IsMovieFavoriteUiState()
        data class Error(val error: String) : IsMovieFavoriteUiState()
    }


}