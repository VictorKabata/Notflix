package com.vickikbt.notflix.ui.fragments.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.vickikbt.data.util.ApiException
import com.vickikbt.data.util.NoInternetException
import com.vickikbt.domain.models.PopularResult
import com.vickikbt.domain.models.UpcomingResult
import com.vickikbt.domain.usecases.FetchMovieDetailsUseCase
import com.vickikbt.domain.usecases.FetchPopularMoviesUsecase
import com.vickikbt.domain.usecases.FetchUpcomingMoviesUsecase
import com.vickikbt.notflix.util.StateListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val fetchPopularMoviesUsecase: FetchPopularMoviesUsecase,
    private val fetchUpcomingMoviesUsecase: FetchUpcomingMoviesUsecase,
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase
) :
    ViewModel() {

    init {
        fetchPopularMovies()
        fetchUpcomingMovies()
    }

    var stateListener: StateListener? = null

    private val _popularMoviesMutableLiveData = MutableLiveData<PopularResult>()
    val popularMovies: LiveData<PopularResult> = _popularMoviesMutableLiveData

    private val _upcomingMoviesMutableLiveData = MutableLiveData<UpcomingResult>()
    val upcomingMovies: LiveData<UpcomingResult> = _upcomingMoviesMutableLiveData

    private fun fetchPopularMovies() {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val popularMoviesResponse = fetchPopularMoviesUsecase.invoke()
                popularMoviesResponse.collect { result ->
                    _popularMoviesMutableLiveData.value = result
                    stateListener?.onSuccess("Fetched popular movies")
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
            }
        }

    }

    private fun fetchUpcomingMovies() {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val upcomingMoviesResponse = fetchUpcomingMoviesUsecase.invoke()
                upcomingMoviesResponse.collect { result ->
                    _upcomingMoviesMutableLiveData.value = result
                    stateListener?.onSuccess("Fetched upcoming movies")
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


    fun fetchMovieDetails(movieId: Int) = liveData {
        stateListener?.onLoading()

        try {
            val movieDetailsFlow = fetchMovieDetailsUseCase.invoke(movieId)
            movieDetailsFlow.collect { movieDetails ->
                stateListener?.onSuccess("Fetched movie details for: ${movieDetails.title}")
                emit(movieDetails)
            }
            return@liveData
        } catch (e: ApiException) {
            stateListener?.onError(e.message!!)
            return@liveData
        } catch (e: NoInternetException) {
            stateListener?.onError(e.message!!)
            return@liveData
        } catch (e: Exception) {
            stateListener?.onError(e.message!!)
            return@liveData
        }

    }
}