package com.vickikbt.notflix.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import com.vickikbt.shared.utils.DetailsUiState
import com.vickikbt.shared.utils.NetworkResultState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) : ViewModel() {

    private val _movieDetailsState = MutableStateFlow(DetailsUiState())
    val movieDetailsState = _movieDetailsState.asStateFlow()

    fun getMovieDetails(movieId: Int) = viewModelScope.launch {
        movieDetailsRepository.fetchMovieDetails(movieId = movieId).collect { movieDetailsResult ->
            when (movieDetailsResult) {
                is NetworkResultState.Loading -> {
                    _movieDetailsState.update { it.copy(isLoading = true) }
                }
                is NetworkResultState.Failure -> {
                    _movieDetailsState.update {
                        it.copy(
                            error = movieDetailsResult.exception.localizedMessage,
                            isLoading = false
                        )
                    }
                }
                is NetworkResultState.Success -> {
                    _movieDetailsState.update {
                        it.copy(
                            movieDetails = movieDetailsResult.data,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun getMovieCast(movieId: Int) = viewModelScope.launch {
        movieDetailsRepository.fetchMovieCast(movieId = movieId).collect { movieCastsResult ->
            when (movieCastsResult) {
                is NetworkResultState.Loading -> {
                    _movieDetailsState.update { it.copy(isLoading = true) }
                }
                is NetworkResultState.Failure -> {
                    _movieDetailsState.update {
                        it.copy(
                            error = movieCastsResult.exception.localizedMessage,
                            isLoading = false
                        )
                    }
                }
                is NetworkResultState.Success -> {
                    _movieDetailsState.update {
                        it.copy(
                            movieCast = movieCastsResult.data.actor,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun fetchSimilarMovies(movieId: Int) = viewModelScope.launch {
        movieDetailsRepository.fetchSimilarMovies(movieId).collect { moviesResult ->
            when (moviesResult) {
                is NetworkResultState.Loading -> {
                    _movieDetailsState.update { it.copy(isLoading = true) }
                }
                is NetworkResultState.Failure -> {
                    _movieDetailsState.update {
                        it.copy(
                            error = moviesResult.exception.localizedMessage,
                            isLoading = false
                        )
                    }
                }
                is NetworkResultState.Success -> {
                    _movieDetailsState.update {
                        it.copy(
                            similarMovies = moviesResult.data,
                            isLoading = false
                        )
                    }
                }
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
