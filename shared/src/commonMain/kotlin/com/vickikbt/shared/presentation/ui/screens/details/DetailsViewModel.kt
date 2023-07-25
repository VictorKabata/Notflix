package com.vickikbt.shared.presentation.ui.screens.details

import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import com.vickikbt.shared.utils.DetailsUiState
import com.vickikbt.shared.utils.isLoading
import com.vickikbt.shared.utils.onFailure
import com.vickikbt.shared.utils.onSuccess
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class DetailsViewModel constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) : KoinComponent {

    private val _movieDetailsState = MutableStateFlow(DetailsUiState())
    val movieDetailsState = _movieDetailsState.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _movieDetailsState.update { it.copy(isLoading = false, error = exception.message) }
    }

    fun getMovieDetails(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        movieDetailsRepository.fetchMovieDetails(movieId = movieId).collect { movieDetailsResult ->
            movieDetailsResult.isLoading { isLoading ->
                _movieDetailsState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movieDetails ->
                _movieDetailsState.update { it.copy(movieDetails = movieDetails) }
            }.onFailure { error ->
                _movieDetailsState.update { it.copy(error = error.message) }
            }
        }
    }

    fun getMovieCast(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        movieDetailsRepository.fetchMovieCast(movieId = movieId).collect { movieCastsResult ->
            movieCastsResult.isLoading { isLoading ->
                _movieDetailsState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { cast ->
                _movieDetailsState.update { it.copy(movieCast = cast.actor) }
            }.onFailure { error ->
                _movieDetailsState.update { it.copy(error = error.message) }
            }
        }
    }

    fun fetchSimilarMovies(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        _movieDetailsState.update { it.copy(isLoading = true) }
        movieDetailsRepository.fetchSimilarMovies(movieId).collect { similarMovies ->
            similarMovies.isLoading { isLoading ->
                _movieDetailsState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _movieDetailsState.update { it.copy(similarMovies = movies) }
            }.onFailure { error ->
                _movieDetailsState.update { it.copy(error = error.message) }
            }
        }
    }

    @Deprecated("Pending caching implementation")
    fun saveMovieDetails(movieDetails: MovieDetails) = viewModelScope.launch {
        try {
            movieDetailsRepository.apply {
                // movieDetailsRepository.saveMovieDetails(movieDetail = movieDetails)
            }
        } catch (e: Exception) {
            Napier.e("Error saving movie: $e")
        }
    }

    @Deprecated("Pending caching implementation")
    fun deleteFavouriteMovie(movieId: Int) = viewModelScope.launch {
        // favouritesPresenter.deleteFavouriteMovie(movieId = movieId)
    }
}
