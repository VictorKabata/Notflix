package com.vickikbt.notflix.ui.fragments.movie_details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.data.util.ApiException
import com.vickikbt.data.util.NoInternetException
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.domain.usecases.FetchMovieDetailsUseCase
import com.vickikbt.domain.usecases.GetMovieDetailsUseCase
import com.vickikbt.notflix.util.StateListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class MovieDetailsViewModel @ViewModelInject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase
) : ViewModel() {

    private val _movieDetailsMutableLiveData = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetailsMutableLiveData

    /*private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> = _movieId*/

    var stateListener: StateListener? = null

    fun getMovieDetails(movieId: Int) {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val getMovieDetailsResponse = getMovieDetailsUseCase.invoke(movieId)

                getMovieDetailsResponse?.collect { movieDetailsLocal ->
                    Timber.e("Fetching from local storage")
                    _movieDetailsMutableLiveData.value = movieDetailsLocal
                    return@collect
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

    fun fetchMovieDetails(movieId: Int) {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val fetchMovieDetailsResponse = fetchMovieDetailsUseCase.invoke(movieId)

                Timber.e("Fetching from local api")

                fetchMovieDetailsResponse.collect { movieDetailsApi ->
                    _movieDetailsMutableLiveData.value = movieDetailsApi
                    return@collect
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