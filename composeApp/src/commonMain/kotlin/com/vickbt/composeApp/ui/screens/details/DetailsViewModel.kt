package com.vickbt.composeApp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vickbt.composeApp.domain.models.MovieDetails
import com.vickbt.composeApp.domain.repositories.MovieDetailsRepository
import com.vickbt.composeApp.utils.DetailsUiState
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

class DetailsViewModel(
    private val movieDetailsRepository: MovieDetailsRepository
) : ViewModel() {

    private val _movieDetailsState = MutableStateFlow(DetailsUiState())
    val movieDetailsState = _movieDetailsState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _movieDetailsState.update { it.copy(isLoading = false, error = exception.message) }
    }

    fun fetchInitialData(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        try {
            withContext(Dispatchers.IO) {
                listOf(
                    async { fetchMovieDetails(movieId = movieId) },
                    async { fetchMovieCast(movieId = movieId) },
                    async { fetchSimilarMovies(movieId = movieId) },
                    async { isMovieFavorite(movieId = movieId) }
                ).awaitAll()
            }
        } catch (e: Exception) {
            _movieDetailsState.update { it.copy(error = e.message, isLoading = false) }
        } finally {
            _movieDetailsState.update { it.copy(isLoading = false) }
        }
    }

    fun fetchMovieDetails(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        movieDetailsRepository.fetchMovieDetails(movieId = movieId).onSuccess { data ->
            data.collectLatest { movieDetails ->
                _movieDetailsState.update { it.copy(movieDetails = movieDetails) }
            }
        }.onFailure { error ->
            _movieDetailsState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchMovieCast(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        movieDetailsRepository.fetchMovieCast(movieId = movieId).onSuccess { data ->
            data.collectLatest { cast -> _movieDetailsState.update { it.copy(movieCast = cast.actor) } }
        }.onFailure { error ->
            _movieDetailsState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun fetchSimilarMovies(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        _movieDetailsState.update { it.copy(isLoading = true) }
        movieDetailsRepository.fetchSimilarMovies(movieId).onSuccess { data ->
            _movieDetailsState.update { it.copy(similarMovies = data.cachedIn(viewModelScope)) }
        }.onFailure { error ->
            _movieDetailsState.update { it.copy(error = error.message, isLoading = false) }
        }
    }

    fun saveFavoriteMovie(movieDetails: MovieDetails) =
        viewModelScope.launch(coroutineExceptionHandler) {
            movieDetailsRepository.saveFavoriteMovie(movie = movieDetails)
        }

    fun deleteFavoriteMovie(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        movieDetailsRepository.deleteFavoriteMovie(movieId = movieId)
    }

    fun isMovieFavorite(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        movieDetailsRepository.isMovieFavorite(movieId = movieId).onSuccess { data ->
            data.collectLatest { isFavorite ->
                _movieDetailsState.update { it.copy(isFavorite = isFavorite) }
            }
        }
    }
}
