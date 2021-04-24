package com.vickikbt.notflix.ui.fragments.movie_details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.data.sources.MovieDetailsDataSource
import com.vickikbt.data.util.ApiException
import com.vickikbt.data.util.NoInternetException
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.domain.usecases.FetchMovieDetailsUseCase
import com.vickikbt.notflix.util.StateListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsViewModel @ViewModelInject constructor(private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase) : ViewModel() {

    private val _movieDetailsMutableLiveData = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetailsMutableLiveData

    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> = _movieId

    var stateListener: StateListener? = null


    fun fetchMovieDetails(movieId:Int) {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val movieDetailsResponse=fetchMovieDetailsUseCase.invoke(movieId)

                movieDetailsResponse.collect { movieDetails->
                    _movieDetailsMutableLiveData.value=movieDetails
                    stateListener?.onSuccess("Movie details: $movieDetails")
                }
                return@launch
            }catch (e: ApiException){
                stateListener?.onError(e.message!!)
                return@launch
            }catch (e: NoInternetException){
                stateListener?.onError(e.message!!)
                return@launch
            }catch (e:Exception){
                stateListener?.onError(e.message!!)
                return@launch
            }
        }
    }

}