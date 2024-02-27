package com.vickbt.shared.utils

import com.vickbt.shared.domain.models.Episode
import com.vickbt.shared.domain.models.Movie

data class MainUiState(
    val selectedTheme: Int? = 0
)

data class HomeUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val featureMovies: List<Movie?>? = emptyList(),
    val trendingMovies: List<Movie?>? = emptyList(),
    val trendingTvShows: List<Movie?>? = emptyList(),
    val latestMovies: List<Movie?>? = emptyList(),
    val latestTvShows: List<Movie?>? = emptyList(),
)

data class DetailsUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val movieDetails: Movie? = null,
    val episodes: List<Episode>? = null,
    val isFavorite: Boolean? = false
)

data class SearchUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val movieResults: List<Movie>? = emptyList()
)

data class FavouritesUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val favoriteMovies: List<Movie>? = emptyList()
)

data class SettingsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedTheme: Int = 0,
    val selectedImageQuality: Int = 0
)
