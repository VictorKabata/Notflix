package com.vickikbt.repository.mappers

import com.vickikbt.cache.models.*
import com.vickikbt.domain.models.*

internal fun MovieDetails.toEntity(): MovieDetailsEntity {
    return MovieDetailsEntity(
        this.adult,
        this.backdropPath,
        this.genres?.map { it.toEntity() },
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
        this.spokenLanguages?.map { it.toEntity() },
        this.status,
        this.tagline,
        this.title,
        this.video,
        this.voteAverage,
        this.voteCount
    )
}

internal fun Genre.toEntity(): GenreEntity {
    return GenreEntity(
        this.id,
        this.name
    )
}

internal fun SpokenLanguage.toEntity(): SpokenLanguageEntity {
    return SpokenLanguageEntity(
        this.englishName,
        this.iso6391,
        this.name
    )
}

internal fun Cast.toEntity(): CastEntity {
    return CastEntity(
        this.actor?.map { it.toEntity() },
        this.id
    )
}

internal fun Actor.toEntity(): ActorEntity {
    return ActorEntity(
        this.castId,
        this.character,
        this.creditId,
        this.id,
        this.name,
        this.originalName,
        this.profilePath
    )
}

internal fun MovieVideo.toEntity(): MovieVideoEntity {
    return MovieVideoEntity(id, videos?.map { it.toEntity() })
}

internal fun Video.toEntity(): VideoEntity {
    return VideoEntity(id, iso31661, iso6391, key, name, official, publishedAt, site, size, type)
}
