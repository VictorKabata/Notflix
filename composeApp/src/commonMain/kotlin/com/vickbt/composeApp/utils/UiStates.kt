package com.vickbt.composeApp.utils

import androidx.paging.PagingData
import com.vickbt.composeApp.domain.models.Actor
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.domain.models.MovieDetails
import com.vickbt.composeApp.domain.models.TvShow
import kotlinx.coroutines.flow.Flow

data class MainUiState(
    val selectedTheme: Int? = 0
)

data class HomeUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val nowPlayingMovies: List<Movie>? = emptyList(),
    val trendingMovies: Flow<PagingData<Movie>>? = null,
    val popularMovies: Flow<PagingData<Movie>>? = null,
    val upcomingMovies: Flow<PagingData<Movie>>? = null,

    val airingTodayTvShows: List<TvShow>? = null,
    val trendingTvShows: Flow<PagingData<TvShow>>? = null,
    val topRatedTvShows: Flow<PagingData<TvShow>>? = null,
    val popularTvShows: Flow<PagingData<TvShow>>? = null
)

data class DetailsUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val movieDetails: MovieDetails? = null,
    val movieCast: List<Actor>? = emptyList(),
    val similarMovies: Flow<PagingData<Movie>>? = null,
    val isFavorite: Boolean? = false
)

data class SearchUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val movieResults: Flow<PagingData<Movie>>? = null
)

data class FavouritesUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val favoriteMovies: List<MovieDetails>? = emptyList()
)

data class SettingsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedTheme: Int = 0,
    val selectedImageQuality: Int = 0
)
