package com.company.details.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.notflix.util.StateListener
import com.vickikbt.repository.models.Cast
import com.vickikbt.repository.models.MovieDetails
import com.vickikbt.repository.models.SimilarResult
import com.vickikbt.repository.models.Video
import com.vickikbt.repository.repositories.movie_details.MovieDetailsRepository
import com.vickikbt.repository.utils.ApiException
import com.vickikbt.repository.utils.NoInternetException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

class MovieDetailsViewModel constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) : ViewModel() {

    private val _movieDetailsMutableLiveData = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetailsMutableLiveData

    private val _castsMutableLiveData = MutableLiveData<Cast>()
    val cast: LiveData<Cast> = _castsMutableLiveData

    private val _videosMutableLiveData = MutableLiveData<Video>()
    val video: LiveData<Video> = _videosMutableLiveData

    private val _similarMoviesMutableLiveData = MutableLiveData<SimilarResult>()
    val similarMovies: LiveData<SimilarResult> = _similarMoviesMutableLiveData

    var stateListener: StateListener? = null

    //Get movieDetails
    fun getMovieDetails(movieId: Int) {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val movieDetailsResponse = movieDetailsRepository.getMovieDetails(movieId)
                movieDetailsResponse!!.collect { movieDetails ->
                    _movieDetailsMutableLiveData.value = movieDetails
                }.also { getMovieCast(movieId = movieId) }
                    .also { getMovieVideos(movieId = movieId) }
                    .also { getSimilarMovies(movieId = movieId) }
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
                movieCastResponse!!.collect { cast ->
                    _castsMutableLiveData.value = cast
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
                movieVideoResponse!!.collect { video ->
                    _videosMutableLiveData.value = video
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
                    _similarMoviesMutableLiveData.value = similarMovies
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


}