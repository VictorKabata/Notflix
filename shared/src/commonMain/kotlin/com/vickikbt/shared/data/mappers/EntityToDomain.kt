package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.cache.sqldelight.ActorEntity
import com.vickikbt.shared.data.cache.sqldelight.CastEntity
import com.vickikbt.shared.data.cache.sqldelight.MovieDetailsEntity
import com.vickikbt.shared.data.cache.sqldelight.MovieEntity
import com.vickikbt.shared.domain.models.Actor
import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails

fun MovieEntity.toDomain(category: String? = null): Movie {
    return Movie(
        adult = this.adult,
        backdropPath = this.backdropPath,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount?.toInt(),
        category = category,
        isFavorite = null,
        cacheId = 0,
        mediaType = this.mediaType
    )
}

fun MovieDetailsEntity.toDomain(): MovieDetails {
    return MovieDetails(
        adult = this.adult,
        backdropPath = this.backdropPath,
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity?.toDouble(),
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        runtime = this.runtime,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage?.toDouble(),
        voteCount = this.voteCount,
        isFavourite = this.isFavourite
    )
}

fun CastEntity.toDomain(): Cast {
    return Cast(
        actor = this.actor.map { it.toDomain() },
        id = this.id
    )
}

fun ActorEntity.toDomain(): Actor {
    return Actor(
        castId = this.castId,
        character = this.character,
        creditId = this.creditId,
        id = this.id,
        name = this.name,
        originalName = this.originalName,
        profilePath = this.profilePath
    )
}
