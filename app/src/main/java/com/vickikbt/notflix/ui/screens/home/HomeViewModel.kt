package com.vickikbt.notflix.ui.screens.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.repository.NowPlayingMoviesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel constructor(private val nowPlayingMoviesRepository: NowPlayingMoviesRepository) :
    ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<List<Movie>>()
    val nowPlayingMovies: LiveData<List<Movie>> get() = _nowPlayingMovies

    init {
        fetchNowPlayingMovies()
    }

    private fun fetchNowPlayingMovies() = viewModelScope.launch {
        try {
            val nowPlayingMoviesResponse = nowPlayingMoviesRepository.fetchNowPlayingMovies()

            nowPlayingMoviesResponse.collect { result ->
                _nowPlayingMovies.value = result
            }
        } catch (e: Exception) {
            Timber.e("Error fetching now playing movies: ${e.localizedMessage}")
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