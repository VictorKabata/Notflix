package com.vickbt.composeApp.data.mappers

import com.vickbt.composeApp.data.cache.entities.MovieDetailsEntity
import com.vickbt.composeApp.domain.models.MovieDetails

fun MovieDetailsEntity.toDomain(): MovieDetails {
    return MovieDetails(
        adult = null,
        backdropPath = this.backdropPath,
        homepage = null,
        id = this.id,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        runtime = this.runtime,
        status = this.status,
        tagline = this.tagLine,
        title = this.title,
        video = null,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}
