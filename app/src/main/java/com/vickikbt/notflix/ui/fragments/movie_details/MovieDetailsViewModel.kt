package com.vickikbt.notflix.ui.fragments.movie_details

import androidx.lifecycle.ViewModel
import com.vickikbt.notflix.util.StateListener

class MovieDetailsViewModel : ViewModel() {

    //private val _movieDetailsMutableLiveData = MutableLiveData<MovieDetails>()
    //val movieDetails: LiveData<MovieDetails> = _movieDetailsMutableLiveData

    //private val _movieId = MutableLiveData<Int>()
    //val movieId: LiveData<Int> = _movieId

    var stateListener: StateListener? = null


    /*fun fetchMovieDetails(movieId:Int) {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val movieDetailsResponse=fetchMovieDetailsUseCase.invoke(movieId)
                movieDetailsResponse.collect { movieDetails->
                    _movieDetailsMutableLiveData.value=movieDetails
                    stateListener?.onSuccess("Movie details: $movieDetails")
                }
                return@launch
            }catch (e:ApiException){
                stateListener?.onError(e.message!!)
                return@launch
            }catch (e:NoInternetException){
                stateListener?.onError(e.message!!)
                return@launch
            }catch (e:Exception){
                stateListener?.onError(e.message!!)
                return@launch
            }
        }
    }*/

}