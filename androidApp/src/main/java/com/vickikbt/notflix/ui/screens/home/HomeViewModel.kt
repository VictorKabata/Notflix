package com.vickikbt.notflix.ui.screens.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.utils.Constants
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<List<Movie>>()
//    val nowPlayingMovies: LiveData<List<Movie>> get() = _nowPlayingMovies

    private val _trendingMovies = MutableLiveData<List<Movie>>()
//    val trendingMovies: LiveData<List<Movie>> get() = _trendingMovies

    private val _popularMovies = MutableLiveData<List<Movie>>()
//    val popularMovies: LiveData<List<Movie>> get() = _popularMovies

    private val _upcomingMovies = MutableLiveData<List<Movie>>()
//    val upcomingMovies: LiveData<List<Movie>> get() = _upcomingMovies

    init {
        /*fetchNowPlayingMovies()
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()*/
    }

    private fun fetchNowPlayingMovies() = viewModelScope.launch {
        try {
            moviesRepository.fetchMovies(category = Constants.CATEGORY_NOW_PLAYING_MOVIES)
                .collectLatest {
                    _nowPlayingMovies.value = it
                }
        } catch (e: Exception) {
            Napier.e("Error fetching trending movies: ${e.localizedMessage}")
        }
    }

    private fun fetchTrendingMovies() = viewModelScope.launch {
        viewModelScope.launch {
            try {
                moviesRepository.fetchMovies(category = Constants.CATEGORY_TRENDING_MOVIES)
                    .collectLatest {
                        _trendingMovies.value = it
                    }
            } catch (e: Exception) {
                Napier.e("Error fetching trending movies: ${e.localizedMessage}")
            }
        }
    }

    private fun fetchPopularMovies() = viewModelScope.launch {
        viewModelScope.launch {
            try {
                moviesRepository.fetchMovies(category = Constants.CATEGORY_POPULAR_MOVIES)
                    .collectLatest {
                        _popularMovies.value = it
                    }
            } catch (e: Exception) {
                Napier.e("Error fetching popular movies: ${e.localizedMessage}")
            }
        }
    }

    private fun fetchUpcomingMovies() = viewModelScope.launch {
        viewModelScope.launch {
            try {
                moviesRepository.fetchMovies(category = Constants.CATEGORY_UPCOMING_MOVIES)
                    .collectLatest {
                        _upcomingMovies.value = it
                    }
            } catch (e: Exception) {
                Napier.e("Error fetching upcoming movies: ${e.localizedMessage}")
            }
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
