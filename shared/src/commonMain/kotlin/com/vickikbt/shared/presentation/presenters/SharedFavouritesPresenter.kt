package com.vickikbt.shared.presentation.presenters

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.core.component.KoinComponent

class SharedFavouritesPresenter constructor(private val favouritesRepository: MoviesRepository) :
    KoinComponent {

    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val _favouriteMovies = MutableStateFlow<List<Movie>?>(emptyList())
    val favouriteMovies get() = _favouriteMovies

    /*private fun getFavoriteMovies() = viewModelScope.launch {
        favouritesRepository.fetchMovies(category = Constants.CATEGORY_UPCOMING_MOVIES)
            .collectLatest { movies ->
                _favouriteMovies.value = movies
            }
    }*/
}
