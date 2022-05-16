package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.network.models.*
import com.vickikbt.shared.domain.models.*

fun MovieDto.toDomain(category: String? = null): Movie {
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
        voteCount = this.voteCount,
        category = category,
        isFavorite = null,
        cacheId = 0,
        mediaType = this.mediaType
    )
}

fun MovieResultsDto.toDomain(): MovieResults {
    return MovieResults(
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

fun GenreDto.toDomain(): Genre {
    return Genre(
        this.id,
        this.name
    )
}

fun SpokenLanguageDto.toDomain(): SpokenLanguage {
    return SpokenLanguage(
        this.englishName,
        this.iso6391,
        this.name
    )
}

fun MovieDetailsDto.toDomain(): MovieDetails {
    return MovieDetails(
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

fun ActorDto.toDomain(): Actor {
    return Actor(
        this.castId,
        this.character,
        this.creditId,
        this.id,
        this.name,
        this.originalName,
        this.profilePath
    )
}

fun CastDto.toDomain(): Cast {
    return Cast(
        this.actor?.map { it.toDomain() },
        this.id
    )
}

fun VideoDto.toDomain(): Video {
    return Video(
        this.id,
        this.iso31661,
        this.iso6391,
        this.key,
        this.name,
        this.official,
        this.publishedAt,
        this.site,
        this.size,
        this.type
    )
}

fun MovieVideoDto.toDomain(): MovieVideo {
    return MovieVideo(
        this.id,
        this.videos?.map { it.toDomain() }
    )
}

fun SimilarMoviesDto.toDomain(): SimilarMovies {
    return SimilarMovies(
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

internal fun DatesDto.toDomain(): Dates {
    return Dates(
        this.maximum,
        this.minimum
    )
}
