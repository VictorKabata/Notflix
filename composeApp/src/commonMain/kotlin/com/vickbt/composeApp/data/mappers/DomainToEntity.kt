package com.vickbt.composeApp.data.mappers

import com.vickbt.composeApp.data.cache.entities.MovieDetailsEntity
import com.vickbt.composeApp.domain.models.MovieDetails
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun MovieDetails.toEntity(): MovieDetailsEntity {
    return MovieDetailsEntity(
        id = this.id,
        imdbId = this.imdbId,
        title = this.title ?: "",
        posterPath = this.posterPath ?: "",
        backdropPath = this.backdropPath ?: "",
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle ?: "",
        overview = this.overview ?: "",
        popularity = this.popularity,
        releaseDate = this.releaseDate,
        runtime = this.runtime,
        status = this.status,
        tagLine = this.tagline,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toString()
    )
}
