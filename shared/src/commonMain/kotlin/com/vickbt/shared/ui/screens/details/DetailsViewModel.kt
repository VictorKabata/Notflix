package com.vickbt.shared.ui.screens.details

import com.vickbt.shared.domain.models.Movie
import com.vickbt.shared.domain.repositories.MovieDetailsRepository
import com.vickbt.shared.utils.DetailsUiState
import com.vickbt.shared.utils.isLoading
import com.vickbt.shared.utils.onFailure
import com.vickbt.shared.utils.onSuccess
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class DetailsViewModel(
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

    fun getShowDetails(showId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        movieDetailsRepository.fetchTvShowDetails(showId = showId).collect { showDetailsResult ->
            showDetailsResult.isLoading { isLoading ->
                _movieDetailsState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { showDetails ->
                _movieDetailsState.update { it.copy(movieDetails = showDetails) }

                getSeasonEpisodes(
                    seasonId = movieDetailsState.value.movieDetails?.seasons?.firstOrNull()?.id
                        ?: ""
                )
            }.onFailure { error ->
                _movieDetailsState.update { it.copy(error = error.message) }
            }
        }
    }

    fun getSeasonEpisodes(seasonId: String) = viewModelScope.launch(coroutineExceptionHandler) {
        movieDetailsRepository.fetchSeasonEpisodes(seasonId = seasonId).collect { episodesResult ->
            episodesResult.isLoading { isLoading ->
                _movieDetailsState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { seasonEpisodes ->
                _movieDetailsState.update { it.copy(episodes = seasonEpisodes) }
            }.onFailure { error ->
                _movieDetailsState.update { it.copy(error = error.message) }
            }

        }
    }

    fun getMovieCast(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        /*movieDetailsRepository.fetchMovieCast(movieId = movieId).collect { movieCastsResult ->
            movieCastsResult.isLoading { isLoading ->
                _movieDetailsState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { cast ->
                _movieDetailsState.update { it.copy(movieCast = cast.actor) }
            }.onFailure { error ->
                _movieDetailsState.update { it.copy(error = error.message) }
            }
        }*/
    }

    fun fetchSimilarMovies(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        /*_movieDetailsState.update { it.copy(isLoading = true) }
        movieDetailsRepository.fetchSimilarMovies(movieId).collect { similarMovies ->
            similarMovies.isLoading { isLoading ->
                _movieDetailsState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _movieDetailsState.update { it.copy(similarMovies = movies) }
            }.onFailure { error ->
                _movieDetailsState.update { it.copy(error = error.message) }
            }
        }*/
    }

    fun saveFavoriteMovie(movieDetails: Movie) =
        viewModelScope.launch(coroutineExceptionHandler) {
            /*try {
                movieDetailsRepository.saveFavoriteMovie(movie = movieDetails)
            } catch (e: Exception) {
                Napier.e("Error saving movie: ${e.message}")
            }*/
        }

    fun deleteFavoriteMovie(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        /*try {
            movieDetailsRepository.deleteFavoriteMovie(movieId = movieId)
        } catch (e: Exception) {
            Napier.e("Error removing movie: ${e.message}")
        }*/
    }

    fun isMovieFavorite(movieId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        /*try {
            val isFavorite = movieDetailsRepository.isMovieFavorite(movieId = movieId)
            _movieDetailsState.update { it.copy(isFavorite = isFavorite) }
        } catch (e: Exception) {
            Napier.e("Error removing movie: ${e.message}")
        }*/
    }
}
