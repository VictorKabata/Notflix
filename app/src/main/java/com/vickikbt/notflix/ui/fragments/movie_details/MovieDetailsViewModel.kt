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

class MovieDetailsViewModel @ViewModelInject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase
) :
    ViewModel() {

    private val _movieDetailsMutableLiveData = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetailsMutableLiveData

    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> = _movieId

    var stateListener: StateListener? = null


    fun fetchMovieDetails(movieId: Int) {
        Timber.e("fetchingMovieDetails viewModel")

        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val getMovieDetailsResponse = getMovieDetailsUseCase.invoke(movieId)
                val fetchMovieDetailsResponse = fetchMovieDetailsUseCase.invoke(movieId)

                getMovieDetailsResponse?.collect { movieDetailsLocal ->
                    if (movieDetailsLocal!=null) _movieDetailsMutableLiveData.value=movieDetailsLocal

                    else{
                        fetchMovieDetailsResponse.collect { movieDetailsApi->
                            _movieDetailsMutableLiveData.value=movieDetailsApi
                        }
                    }
                }
                return@launch
            } catch (e: ApiException) {
                stateListener?.onError(e.message!!)
                return@launch
            } catch (e: NoInternetException) {
                stateListener?.onError(e.message!!)
                return@launch
            } catch (e: Exception) {
                stateListener?.onError(e.message!!)
                return@launch
            }
        }
    }


}