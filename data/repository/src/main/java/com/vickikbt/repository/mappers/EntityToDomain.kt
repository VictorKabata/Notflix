package com.vickikbt.repository.mappers

import com.vickikbt.cache.models.*
import com.vickikbt.domain.models.*
import com.vickikbt.network.models.*

fun DatesEntity.toDomain(): com.vickikbt.shared.domain.models.Dates {
    return com.vickikbt.shared.domain.models.Dates(
        this.maximum,
        this.minimum
    )
}

fun MovieEntity.toDomain(): com.vickikbt.shared.domain.models.Movie {
    return com.vickikbt.shared.domain.models.Movie(
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

fun NowPlayingMoviesEntity.toDomain(): com.vickikbt.shared.domain.models.NowPlayingMovies {
    return com.vickikbt.shared.domain.models.NowPlayingMovies(
        this.dates?.toDomain(),
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

fun PopularMoviesEntity.toDomain(): com.vickikbt.shared.domain.models.PopularMovies {
    return com.vickikbt.shared.domain.models.PopularMovies(
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

fun TrendingMoviesEntity.toDomain(): com.vickikbt.shared.domain.models.TrendingMovies {
    return com.vickikbt.shared.domain.models.TrendingMovies(
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

fun UpcomingMoviesEntity.toDomain(): com.vickikbt.shared.domain.models.UpcomingMovies {
    return com.vickikbt.shared.domain.models.UpcomingMovies(
        this.dates?.toDomain(),
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}

fun GenreEntity.toDomain(): com.vickikbt.shared.domain.models.Genre {
    return com.vickikbt.shared.domain.models.Genre(
        this.id,
        this.name
    )
}

fun SpokenLanguageEntity.toDomain(): com.vickikbt.shared.domain.models.SpokenLanguage {
    return com.vickikbt.shared.domain.models.SpokenLanguage(
        this.englishName,
        this.iso6391,
        this.name
    )
}

fun MovieDetailsEntity.toDomain(): com.vickikbt.shared.domain.models.MovieDetails {
    return com.vickikbt.shared.domain.models.MovieDetails(
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

fun ActorEntity.toDomain(): com.vickikbt.shared.domain.models.Actor {
    return com.vickikbt.shared.domain.models.Actor(
        this.castId,
        this.character,
        this.creditId,
        this.id,
        this.name,
        this.originalName,
        this?.profilePath
    )
}

fun CastEntity.toDomain(): com.vickikbt.shared.domain.models.Cast {
    return com.vickikbt.shared.domain.models.Cast(
        this.actor?.map { it.toDomain() },
        this.id
    )
}

fun VideoEntity.toDomain(): com.vickikbt.shared.domain.models.Video {
    return com.vickikbt.shared.domain.models.Video(
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

fun MovieVideoEntity.toDomain(): com.vickikbt.shared.domain.models.MovieVideo {
    return com.vickikbt.shared.domain.models.MovieVideo(
        this.id,
        this.videos?.map { it.toDomain() }
    )
}

fun SimilarMoviesEntity.toDomain(): com.vickikbt.shared.domain.models.SimilarMovies {
    return com.vickikbt.shared.domain.models.SimilarMovies(
        this.page,
        this.movies?.map { it.toDomain() },
        this.totalPages,
        this.totalResults
    )
}
