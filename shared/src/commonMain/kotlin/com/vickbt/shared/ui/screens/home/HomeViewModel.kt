package com.vickbt.shared.presentation.ui.screens.home

import com.vickbt.shared.domain.repositories.MoviesRepository
import com.vickbt.shared.domain.utils.Enums.MovieCategories
import com.vickbt.shared.utils.HomeUiState
import com.vickbt.shared.utils.SearchUiState
import com.vickbt.shared.utils.isLoading
import com.vickbt.shared.utils.onFailure
import com.vickbt.shared.utils.onSuccess
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class HomeViewModel(private val moviesRepository: MoviesRepository) : KoinComponent {

    private val _homeUiState = MutableStateFlow(HomeUiState(isLoading = true))
    val homeUiState = _homeUiState.asStateFlow()

    private val _searchUiState = MutableStateFlow(SearchUiState(isLoading = false))
    val searchUiState = _searchUiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _homeUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    fun fetchHomePage() = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.fetchHomePage().collect { results ->
            results.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _homeUiState.update {
                    it.copy(
                        featureMovies = movies[MovieCategories.FEATURED],
                        trendingMovies = movies[MovieCategories.TRENDING_MOVIES],
                        trendingTvShows = movies[MovieCategories.TRENDING_TV_SHOW],
                        latestMovies = movies[MovieCategories.LATEST_MOVIE],
                        latestTvShows = movies[MovieCategories.LATEST_TV_SHOW]
                    )
                }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.message) }
            }
        }
    }

    fun searchMovie(movieName: String) = viewModelScope.launch(coroutineExceptionHandler) {
        /*moviesRepository.searchMovie(movieName = movieName).collectLatest { moviesResult ->
            moviesResult.isLoading { isLoading ->
                _searchUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _searchUiState.update { it.copy(movieResults = movies) }
            }.onFailure { error ->
                _searchUiState.update { it.copy(error = error.message) }
            }
        }*/
    }

    fun updateSearchQuery(searchQuery: String) {
        _searchQuery.value = searchQuery
    }
}
