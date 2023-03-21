package ui.screens.details

import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.models.MovieVideo
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class DetailsViewModel constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) : KoinComponent {

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

    /*fun getMovieDetails(movieId: Int) = CoroutineScope(Dispatchers.IO).launch {
        movieDetailsRepository.fetchMovieDetails(movieId = movieId)
            .collect { movieDetailsResult ->
                movieDetailsResult.onSuccess {
                    _movieDetails.value = it
                }.onFailure {
                    _error.value = it.message
                }
            }
    }

    fun getMovieCast(movieId: Int) = CoroutineScope(Dispatchers.IO).launch {
        movieDetailsRepository.fetchMovieCast(movieId = movieId).collect { movieCastsResult ->
            movieCastsResult.onSuccess {
                _movieCast.value = it
            }.onFailure {
                _error.value = it.message
            }
        }
    }

    fun fetchSimilarMovies(movieId: Int) = CoroutineScope(Dispatchers.IO).launch {
        movieDetailsRepository.fetchSimilarMovies(movieId).collect { moviesResult ->
            moviesResult.onSuccess {
                _similarMovies.value = it
            }.onFailure {
                _error.value = it.message
            }
        }
    }*/

    @Deprecated("Pending caching implementation")
    fun saveMovieDetails(movieDetails: MovieDetails) = CoroutineScope(Dispatchers.IO).launch {
        try {
            movieDetailsRepository.apply {
                // movieDetailsRepository.saveMovieDetails(movieDetail = movieDetails)
            }
        } catch (e: Exception) {
            Napier.e("Error saving movie: $e")
        }
    }

    @Deprecated("Pending caching implementation")
    fun deleteFavouriteMovie(movieId: Int) = CoroutineScope(Dispatchers.IO).launch {
        // favouritesPresenter.deleteFavouriteMovie(movieId = movieId)
    }
}
