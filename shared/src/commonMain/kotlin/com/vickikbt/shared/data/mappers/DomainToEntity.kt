package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.cache.sqldelight.MovieDetailsEntity
import com.vickikbt.shared.domain.models.MovieDetails

fun MovieDetails.toEntity(): MovieDetailsEntity {
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
