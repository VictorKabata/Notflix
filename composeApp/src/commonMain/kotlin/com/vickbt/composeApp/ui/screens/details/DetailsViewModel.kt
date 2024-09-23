package com.vickbt.composeApp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.composeApp.domain.models.MovieDetails
import com.vickbt.composeApp.domain.repositories.MovieDetailsRepository
import com.vickbt.composeApp.utils.DetailsUiState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val movieDetailsRepository: MovieDetailsRepository
) : ViewModel() {

    private val _movieDetailsState = MutableStateFlow(DetailsUiState())
    val movieDetailsState = _movieDetailsState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _movieDetailsState.update { it.copy(isLoading = false, error = exception.message) }
    }

    fun getMovieDetails(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        movieDetailsRepository.fetchMovieDetails(movieId = movieId).onSuccess { data ->
            data.collectLatest { movieDetails ->
                _movieDetailsState.update {
                    it.copy(movieDetails = movieDetails, isLoading = false)
                }
            }
        }.onFailure { error ->
            _movieDetailsState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun getMovieCast(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        movieDetailsRepository.fetchMovieCast(movieId = movieId).onSuccess { data ->
            data.collectLatest { cast ->
                _movieDetailsState.update { it.copy(movieCast = cast.actor, isLoading = false) }
            }
        }.onFailure { error ->
            _movieDetailsState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchSimilarMovies(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        _movieDetailsState.update { it.copy(isLoading = true) }
        movieDetailsRepository.fetchSimilarMovies(movieId).onSuccess { data ->
            data.collectLatest { movies ->
                _movieDetailsState.update { it.copy(similarMovies = movies, isLoading = false) }
            }
        }.onFailure { error ->
            _movieDetailsState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun saveFavoriteMovie(movieDetails: MovieDetails) =
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
                movieDetailsRepository.saveFavoriteMovie(movie = movieDetails)
            } catch (e: Exception) {
                Napier.e("Error saving movie: ${e.message}")
            }
        }

    fun deleteFavoriteMovie(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        try {
            movieDetailsRepository.deleteFavoriteMovie(movieId = movieId)
        } catch (e: Exception) {
            Napier.e("Error removing movie: ${e.message}")
        }
    }

    fun isMovieFavorite(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        try {
            movieDetailsRepository.isMovieFavorite(movieId = movieId).onSuccess { data ->
                data.collectLatest { isFavorite ->
                    _movieDetailsState.update { it.copy(isFavorite = isFavorite) }
                }
            }
        } catch (e: Exception) {
            Napier.e("Error removing movie: ${e.message}")
        }
    }
}
