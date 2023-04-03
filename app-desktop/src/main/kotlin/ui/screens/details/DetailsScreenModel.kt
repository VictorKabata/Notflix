package ui.screens.details

import cafe.adriel.voyager.core.model.ScreenModel
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

class DetailsScreenModel constructor(private val movieDetailsRepository: MovieDetailsRepository) :
    ScreenModel {

    private val _movieDetailsState = MutableStateFlow(DetailsUiState())
    val movieDetailsState = _movieDetailsState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _movieDetailsState.update { it.copy(isLoading = false, error = exception.message) }
    }

    init {
        DetailsUiState(
            isLoading = true,
            error = null,
            movieDetails = null,
            movieCast = null,
            similarMovies = null
        )
    }

    fun getMovieDetails(movieId: Int) =
        CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
            movieDetailsRepository.fetchMovieDetails(movieId = movieId)
                .collect { movieDetailsResult ->
                    movieDetailsResult.isLoading { isLoading ->
                        _movieDetailsState.update { it.copy(isLoading = isLoading) }
                    }.onSuccess { movieDetails ->
                        _movieDetailsState.update { it.copy(movieDetails = movieDetails) }
                    }.onFailure { error ->
                        _movieDetailsState.update { it.copy(error = error.localizedMessage) }
                    }
                }
        }

    fun getMovieCast(movieId: Int) =
        CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
            movieDetailsRepository.fetchMovieCast(movieId = movieId).collect { movieCastsResult ->
                movieCastsResult.isLoading { isLoading ->
                    _movieDetailsState.update { it.copy(isLoading = isLoading) }
                }.onSuccess { cast ->
                    _movieDetailsState.update { it.copy(movieCast = cast.actor) }
                }.onFailure { error ->
                    _movieDetailsState.update { it.copy(error = error.localizedMessage) }
                }
            }
        }

    fun fetchSimilarMovies(movieId: Int) =
        CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
            _movieDetailsState.update { it.copy(isLoading = true) }
            movieDetailsRepository.fetchSimilarMovies(movieId).collect { similarMovies ->
                similarMovies.isLoading { isLoading ->
                    _movieDetailsState.update { it.copy(isLoading = isLoading) }
                }.onSuccess { movies ->
                    _movieDetailsState.update { it.copy(similarMovies = movies) }
                }.onFailure { error ->
                    _movieDetailsState.update { it.copy(error = error.localizedMessage) }
                }
            }
        }

    @Deprecated("Pending caching implementation")
    fun saveMovieDetails(movieDetails: MovieDetails) =
        CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
            try {
                movieDetailsRepository.apply {
                    // movieDetailsRepository.saveMovieDetails(movieDetail = movieDetails)
                }
            } catch (e: Exception) {
                Napier.e("Error saving movie: $e")
            }
        }

    @Deprecated("Pending caching implementation")
    fun deleteFavouriteMovie(movieId: Int) =
        CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
            // favouritesPresenter.deleteFavouriteMovie(movieId = movieId)
        }
}
