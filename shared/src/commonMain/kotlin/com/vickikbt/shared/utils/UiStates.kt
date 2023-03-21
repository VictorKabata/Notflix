package com.vickikbt.shared.utils

import com.vickikbt.shared.domain.models.Actor
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails

data class HomeUiStates(
    val isLoading: Boolean = false,
    val error: String? = null,
    val nowPlayingMovies: List<Movie>? = emptyList(),
    val trendingMovies: List<Movie>? = emptyList(),
    val popularMovies: List<Movie>? = emptyList(),
    val upcomingMovies: List<Movie>? = emptyList(),
)

data class DetailsUiStates(
    val isLoading: Boolean = false,
    val error: String? = null,
    val movieDetails: MovieDetails? = null,
    val movieCast: List<Actor>? = emptyList(),
    val similarMovies: List<Movie>? = emptyList()
)
