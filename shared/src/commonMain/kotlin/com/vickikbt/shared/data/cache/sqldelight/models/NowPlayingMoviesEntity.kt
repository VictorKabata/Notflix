package com.vickikbt.shared.data.cache.sqldelight.models

import com.vickikbt.cache.models.DatesEntity
import com.vickikbt.shared.data.cache.sqldelight.MovieEntity

data class NowPlayingMoviesEntity(

    val dates: DatesEntity?,

    val page: Int?,

    val movies: List<MovieEntity>?,

    val totalPages: Int?,

    val totalResults: Int?
)
