package com.vickikbt.notflix.ui.screens.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.palette.graphics.Palette
import com.vickikbt.cache.models.MovieEntity
import com.vickikbt.shared.domain.utils.Constants
import com.vickikbt.repository.repository.movies_repository.MoviesRepository
import com.vickikbt.shared.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<List<Movie>>()
    val nowPlayingMovies: LiveData<List<Movie>> get() = _nowPlayingMovies

    private val _trendingMovies = MutableLiveData<Flow<PagingData<MovieEntity>>>()
    val trendingMovies: LiveData<Flow<PagingData<MovieEntity>>> get() = _trendingMovies

    private val _popularMovies = MutableLiveData<Flow<PagingData<MovieEntity>>>()
    val popularMovies: LiveData<Flow<PagingData<MovieEntity>>> get() = _popularMovies

    private val _upcomingMovies = MutableLiveData<Flow<PagingData<MovieEntity>>>()
    val upcomingMovies: LiveData<Flow<PagingData<MovieEntity>>> get() = _upcomingMovies

    init {
        fetchNowPlayingMovies()
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()
    }

    private fun fetchNowPlayingMovies() = viewModelScope.launch {
        try {
            val response = moviesRepository.fetchNowPlayingMovies()
            response.collectLatest {
                _nowPlayingMovies.value = it
            }
        } catch (e: Exception) {
            // Timber.e("Error fetching now playing movies: ${e.localizedMessage}")
        }
    }

    private fun fetchTrendingMovies() = viewModelScope.launch {
        try {
            _trendingMovies.value =
                moviesRepository.fetchMovies(category = Constants.CATEGORY_TRENDING_MOVIES)
        } catch (e: Exception) {
            // Timber.e("Error fetching trending movies: ${e.localizedMessage}")
        }
    }

    private fun fetchPopularMovies() = viewModelScope.launch {
        try {
            _popularMovies.value =
                moviesRepository.fetchMovies(category = Constants.CATEGORY_POPULAR_MOVIES)
        } catch (e: Exception) {
            // Timber.e("Error fetching popular movies: ${e.localizedMessage}")
        }
    }

    private fun fetchUpcomingMovies() = viewModelScope.launch {
        try {
            _upcomingMovies.value =
                moviesRepository.fetchMovies(category = Constants.CATEGORY_UPCOMING_MOVIES)
        } catch (e: Exception) {
            // Timber.e("Error fetching upcoming movies: ${e.localizedMessage}")
        }
    }

    fun getImagePalette(drawable: Drawable, onGenerated: (Palette.Swatch) -> Unit) {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bitmap).generate { palette ->
            val vibrantSwatch = palette?.vibrantSwatch
            val dominantSwatch = palette?.vibrantSwatch

            if (vibrantSwatch != null) {
                onGenerated(vibrantSwatch)
            } else if (dominantSwatch != null) {
                onGenerated(dominantSwatch)
            }
        }
    }
}
