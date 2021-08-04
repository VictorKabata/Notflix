package com.company.favorites.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.repository.FavoritesRepository
import com.vickikbt.notflix.util.StateListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoritesViewModel constructor(private val favoritesRepository: FavoritesRepository):ViewModel() {

    var stateListener: StateListener?=null

    private val _favoriteMovies=MutableLiveData<List<Movie>>()
    val favoriteMovies get() = _favoriteMovies

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies(){
        stateListener?.onLoading()

        viewModelScope.launch {
            try {
                val response=favoritesRepository.getFavoriteMovies()
                response.collect {
                    _favoriteMovies.value=it
                    stateListener?.onSuccess("Fetched favorite movies")
                }
                return@launch
            }catch (e:Exception){
                stateListener?.onError(e.message)
                return@launch
            }
        }
    }


}