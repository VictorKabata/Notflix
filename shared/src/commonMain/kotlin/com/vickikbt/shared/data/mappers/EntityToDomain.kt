package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.cache.sqldelight.MovieEntity
import com.vickikbt.shared.domain.models.Movie

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

/*fun MovieDetailsEntity.toDomain(): MovieDetails {
    return MovieDetails(
        adult = this.adult,
        backdropPath = this.backdropPath,
        homepage = this.homepage,
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
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
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

fun CastEntity.toDomain(): Cast {
    return Cast(
        actor = this.actor?.map { it.toDomain() },
        id = this.id
    )
}

fun VideoEntity.toDomain(): Video {
    return Video(
        id = this.id,
        iso31661 = this.iso31661,
        iso6391 = this.iso6391,
        key = this.key,
        name = this.name,
        official = this.official,
        publishedAt = this.publishedAt,
        site = this.site,
        size = this.size,
        type = this.type
    )
}

fun MovieVideoEntity.toDomain(): MovieVideo {
    return MovieVideo(
        id = this.id,
        videos = this.videos?.map { it.toDomain() }
    )
}*/
