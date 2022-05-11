package com.vickikbt.notflix.ui.screens.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.domain.models.Movie
import com.vickikbt.repository.repository.favorites_repository.FavoritesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    init {
        getFavoriteMovies()
    }

    private val _favouriteMovies = MutableLiveData<List<Movie>>()
    val favouriteMovies: LiveData<List<Movie>> get() = _favouriteMovies

    private fun getFavoriteMovies() = viewModelScope.launch {
        favoritesRepository.getFavoriteMovies().collect { movies ->
            _favouriteMovies.value = movies
        }
    }
}
