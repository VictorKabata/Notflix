package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.cache.sqldelight.ActorEntity
import com.vickikbt.shared.data.cache.sqldelight.MovieDetailsEntity
import com.vickikbt.shared.domain.models.Actor
import com.vickikbt.shared.domain.models.MovieDetails

fun MovieDetails.toEntity(): MovieDetailsEntity {
    return MovieDetailsEntity(
        adult = this.adult,
        backdropPath = this.backdropPath,
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdbId!!,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity?.toInt(),
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        runtime = this.runtime,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage?.toInt(),
        voteCount = this.voteCount
    )
}

fun Actor.toEntity(): ActorEntity {
    return ActorEntity(
        castId = this.castId,
        character = this.character,
        creditId = this.creditId,
        id = this.id,
        name = this.name,
        originalName = this.originalName,
        profilePath = this.profilePath
    )
}
