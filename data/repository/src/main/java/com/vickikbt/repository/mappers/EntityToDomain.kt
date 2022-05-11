package com.vickikbt.repository.mappers

import com.vickikbt.cache.models.*
import com.vickikbt.shared.domain.models.*

fun DatesEntity.toDomain(): Dates {
    return Dates(
        this.maximum,
        this.minimum
    )
}

fun MovieEntity.toDomain(): Movie {
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
        isFavorite = null,
        this.cacheId
    )
}

fun NowPlayingMoviesEntity.toDomain(): NowPlayingMovies {
    return NowPlayingMovies(
        this.dates?.toDomain(),
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

fun PopularMoviesEntity.toDomain(): PopularMovies {
    return PopularMovies(
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

fun TrendingMoviesEntity.toDomain(): TrendingMovies {
    return TrendingMovies(
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

fun UpcomingMoviesEntity.toDomain(): UpcomingMovies {
    return UpcomingMovies(
        this.dates?.toDomain(),
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

fun GenreEntity.toDomain(): Genre {
    return Genre(
        this.id,
        this.name
    )
}

fun SpokenLanguageEntity.toDomain(): SpokenLanguage {
    return SpokenLanguage(
        this.englishName,
        this.iso6391,
        this.name
    )
}

fun MovieDetailsEntity.toDomain(): MovieDetails {
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

fun ActorEntity.toDomain(): Actor {
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

fun CastEntity.toDomain(): Cast {
    return Cast(
        this.actor?.map { it.toDomain() },
        this.id
    )
}

fun VideoEntity.toDomain(): Video {
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

fun MovieVideoEntity.toDomain(): MovieVideo {
    return MovieVideo(
        this.id,
        this.videos?.map { it.toDomain() }
    )
}

fun SimilarMoviesEntity.toDomain(): SimilarMovies {
    return SimilarMovies(
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}
