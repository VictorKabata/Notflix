package com.company.details.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.data.utils.ApiException
import com.vickikbt.data.utils.NoInternetException
import com.vickikbt.domain.models.*
import com.vickikbt.domain.repository.FavoritesRepository
import com.vickikbt.domain.repository.MovieDetailsRepository
import com.vickikbt.notflix.util.StateListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class MovieDetailsViewModel constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val _casts = MutableLiveData<Cast>()
    val cast: LiveData<Cast> = _casts

    private val _videos = MutableLiveData<MovieVideo>()
    val video: LiveData<MovieVideo> = _videos

    private val _similarMovies = MutableLiveData<SimilarMovies>()
    val similarMovies: LiveData<SimilarMovies> = _similarMovies

    private val _isMovieFavorite = MutableLiveData<Boolean?>()
    val isMovieFavorite: LiveData<Boolean?> = _isMovieFavorite

    var stateListener: StateListener? = null

    //Get movieDetails
    fun getMovieDetails(movieId: Int) {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val movieDetailsResponse = movieDetailsRepository.getMovieDetails(movieId)
                movieDetailsResponse.collect { movieDetails ->
                    _movieDetails.value = movieDetails

                    isMovieFavorite(movieId = movieId)
                    getMovieCast(movieId = movieId)
                    getMovieVideos(movieId = movieId)
                    getSimilarMovies(movieId = movieId)
                }
                return@launch
            } catch (e: ApiException) {
                stateListener?.onError("${e.message}")
                return@launch
            } catch (e: NoInternetException) {
                stateListener?.onError("${e.message}")
                return@launch
            } catch (e: IOException) {
                stateListener?.onError("${e.message}")
                return@launch
            } catch (e: Exception) {
                stateListener?.onError("${e.message}")
                return@launch
            }
        }
    }

    //Get movie cast
    private fun getMovieCast(movieId: Int) {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val movieCastResponse = movieDetailsRepository.getMovieCast(movieId)
                movieCastResponse.collect { cast ->
                    _casts.value = cast
                }
                return@launch
            } catch (e: ApiException) {
                stateListener?.onError("${e.message}")
                return@launch
            } catch (e: NoInternetException) {
                stateListener?.onError("${e.message}")
                return@launch
            } catch (e: IOException) {
                stateListener?.onError("${e.message}")
                return@launch
            } catch (e: Exception) {
                stateListener?.onError("${e.message}")
                return@launch
            }
        }
    }

    //Get movie videos
    private fun getMovieVideos(movieId: Int) {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val movieVideoResponse = movieDetailsRepository.getMovieVideos(movieId)
                movieVideoResponse.collect { video ->
                    _videos.value = video
                }
                return@launch
            } catch (e: ApiException) {
                stateListener?.onError("${e.message}")
                return@launch
            } catch (e: NoInternetException) {
                stateListener?.onError("${e.message}")
                return@launch
            } catch (e: IOException) {
                stateListener?.onError("${e.message}")
                return@launch
            } catch (e: Exception) {
                stateListener?.onError("${e.message}")
                return@launch
            }
        }
    }

    //Get similar movies
    private fun getSimilarMovies(movieId: Int) {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val similarMoviesResponse = movieDetailsRepository.fetchSimilarMovies(movieId)
                similarMoviesResponse.collect { similarMovies ->
                    _similarMovies.value = similarMovies
                }
                return@launch
            } catch (e: ApiException) {
                stateListener?.onError("${e.message}")
                return@launch
            } catch (e: NoInternetException) {
                stateListener?.onError("${e.message}")
                return@launch
            } catch (e: Exception) {
                stateListener?.onError("${e.message}")
                return@launch
            }
        }
    }

    private fun isMovieFavorite(movieId: Int) {
        viewModelScope.launch {
            val response = favoritesRepository.isMovieFavorite(movieId)
            response.collect {
                _isMovieFavorite.value = it
            }
        }
    }

    fun updateIsMovieFavorite(cacheId: Int, isFavorite: Boolean) {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                favoritesRepository.updateIsMovieFavorite(cacheId, isFavorite)
            } catch (e: Exception) {
                Timber.e("Error updating isFav: ${e.message}")
            }
        }
    }


}