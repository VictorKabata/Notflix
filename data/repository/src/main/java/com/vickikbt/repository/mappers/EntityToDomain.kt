package com.vickikbt.repository.mappers

import com.vickikbt.cache.models.*
import com.vickikbt.domain.models.*
import com.vickikbt.network.models.*

internal fun DatesEntity.toDomain(): Dates {
    return Dates(
        this.maximum,
        this.minimum
    )
}

internal fun MovieEntity.toDomain(): Movie {
    return Movie(
        this.adult,
        this.backdropPath,
        this.genreIds,
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
        category = null,
        isFavorite = null
    )
}


internal fun NowPlayingMoviesEntity.toDomain(): NowPlayingMovies {
    return NowPlayingMovies(
        this.dates?.toDomain(),
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

internal fun PopularMoviesEntity.toDomain(): PopularMovies {
    return PopularMovies(
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

internal fun TrendingMoviesEntity.toDomain(): TrendingMovies {
    return TrendingMovies(
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

internal fun UpcomingMoviesEntity.toDomain(): UpcomingMovies {
    return UpcomingMovies(
        this.dates?.toDomain(),
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

internal fun GenreEntity.toDomain(): Genre {
    return Genre(
        this.id,
        this.name
    )
}

internal fun SpokenLanguageEntity.toDomain(): SpokenLanguage {
    return SpokenLanguage(
        this.englishName,
        this.iso6391,
        this.name
    )
}

internal fun MovieDetailsEntity.toDomain(): MovieDetails {
    return MovieDetails(
        this.adult,
        this.backdropPath,
        this.genres?.map { it.toDomain() },
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
        this.spokenLanguages?.map { it.toDomain() },
        this.status,
        this.tagline,
        this.title,
        this.video,
        this.voteAverage,
        this.voteCount
    )
}

internal fun ActorEntity.toDomain(): Actor {
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

internal fun CastEntity.toDomain(): Cast {
    return Cast(
        this.actor?.map { it.toDomain() },
        this.id
    )
}

internal fun VideoEntity.toDomain(): Video {
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

internal fun MovieVideoEntity.toDomain(): MovieVideo {
    return MovieVideo(
        this.id,
        this.videos?.map { it.toDomain() }
    )
}

internal fun SimilarMoviesEntity.toDomain(): SimilarMovies {
    return SimilarMovies(
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}