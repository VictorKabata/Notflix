package com.company.home.ui.fragments

import androidx.lifecycle.*
import com.vickikbt.data.utils.ApiException
import com.vickikbt.data.utils.NoInternetException
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.repository.*
import com.vickikbt.notflix.util.StateListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val nowPlayingMoviesRepository: NowPlayingMoviesRepository,
    private val popularMoviesRepository: PopularMoviesRepository,
    private val trendingMoviesRepository: TrendingMoviesRepository,
    private val upcomingMoviesMoviesRepository: UpcomingMoviesRepository
) : ViewModel() {

    var stateListener: StateListener? = null

    private val _nowPlayingMoviesMutableLiveData = MutableLiveData<List<Movie>>()
    val nowPlayingMovies: LiveData<List<Movie>> = _nowPlayingMoviesMutableLiveData

    private val _trendingMoviesMutableLiveData = MutableLiveData<List<Movie>>()
    val trendingMovies: LiveData<List<Movie>> = _trendingMoviesMutableLiveData

    private val _popularMoviesMutableLiveData = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> = _popularMoviesMutableLiveData

    private val _upcomingMoviesMutableLiveData = MutableLiveData<List<Movie>>()
    val upcomingMovies: LiveData<List<Movie>> = _upcomingMoviesMutableLiveData

    init {
        fetchNowPlayingMovies()
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()
    }

    private fun fetchNowPlayingMovies() {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val nowPlayingMoviesResponse = nowPlayingMoviesRepository.fetchNowPlayingMovies()

                nowPlayingMoviesResponse.collect { result ->
                    _nowPlayingMoviesMutableLiveData.value = result
                    stateListener?.onSuccess("Fetched now playing movies")
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

    private fun fetchTrendingMovies() {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val trendingMoviesResponse = trendingMoviesRepository.fetchTrendingMovies()

                trendingMoviesResponse.collect { result ->
                    _trendingMoviesMutableLiveData.value = result
                    stateListener?.onSuccess("Fetched trending movies")
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

    private fun fetchPopularMovies() {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val popularMoviesResponse = popularMoviesRepository.fetchPopularMovies()

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
                return@launch
            }
        }

    }

    private fun fetchUpcomingMovies() {
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val upcomingMoviesResponse = upcomingMoviesMoviesRepository.fetchUpcomingMovies()

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
}