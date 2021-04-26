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
import com.vickikbt.notflix.util.StateListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

class MovieDetailsViewModel @ViewModelInject constructor(
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase
) : ViewModel() {

    private val _movieDetailsMutableLiveData = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetailsMutableLiveData

    var stateListener: StateListener? = null

    fun getMovieDetails(movieId: Int) {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val movieDetailsResponse = fetchMovieDetailsUseCase.invoke(movieId)
                movieDetailsResponse!!.collect { movieDetails ->
                    _movieDetailsMutableLiveData.value = movieDetails
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