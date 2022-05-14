package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.cache.sqldelight.MovieDetailsEntity
import com.vickikbt.shared.data.cache.sqldelight.MovieEntity
import com.vickikbt.shared.data.network.models.MovieDetailsDto
import com.vickikbt.shared.data.network.models.MovieDto

fun MovieDto.toEntity(category: String? = null): MovieEntity {
    return MovieEntity(
        this.adult,
        this.backdropPath,
        this.id,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.releaseDate,
        this.title,
        this.video,
        this.voteAverage,
        this.voteCount,
        category = category,
        isFavourite = false,
        cacheId = 0
    )
}

fun MovieDetailsDto.toEntity(): MovieDetailsEntity {
    return MovieDetailsEntity(
        this.adult,
        this.backdropPath,
        this.homepage,
        this.id,
        this.imdbId,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.releaseDate,
        this.runtime,
        this.status,
        this.tagline,
        this.title,
        this.video,
        this.voteAverage,
        this.voteCount
    )
}
