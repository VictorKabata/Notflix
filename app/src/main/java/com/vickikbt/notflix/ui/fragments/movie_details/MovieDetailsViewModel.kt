package com.vickikbt.notflix.ui.fragments.movie_details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.data.util.ApiException
import com.vickikbt.data.util.NoInternetException
import com.vickikbt.domain.models.Cast
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.domain.models.Video
import com.vickikbt.domain.usecases.FetchMovieCastUseCase
import com.vickikbt.domain.usecases.FetchMovieDetailsUseCase
import com.vickikbt.domain.usecases.FetchMovieVideoUseCase
import com.vickikbt.notflix.util.StateListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

class MovieDetailsViewModel @ViewModelInject constructor(
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
    private val fetchMovieCastUseCase: FetchMovieCastUseCase,
    private val fetchMovieVideoUseCase: FetchMovieVideoUseCase
) : ViewModel() {

    private val _movieDetailsMutableLiveData = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetailsMutableLiveData

    private val _castsMutableLiveData = MutableLiveData<Cast>()
    val cast: LiveData<Cast> = _castsMutableLiveData

    private val _videosMutableLiveData = MutableLiveData<Video>()
    val video: LiveData<Video> = _videosMutableLiveData

    var stateListener: StateListener? = null

    fun getMovieDetails(movieId: Int) {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val movieDetailsResponse = fetchMovieDetailsUseCase.invoke(movieId)
                movieDetailsResponse!!.collect { movieDetails ->
                    _movieDetailsMutableLiveData.value = movieDetails
                }.also { getMovieCast(movieId = movieId) }
                    .also { getMovieVideos(movieId = movieId) }
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
                val movieCastResponse = fetchMovieCastUseCase.invoke(movieId)
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
                val movieVideoResponse = fetchMovieVideoUseCase.invoke(movieId)
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


}