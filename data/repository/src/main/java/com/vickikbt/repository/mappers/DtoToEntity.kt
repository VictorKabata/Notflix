package com.vickikbt.repository.mappers

import com.vickikbt.cache.models.*
import com.vickikbt.network.models.*

internal fun DatesDto.toEntity(): DatesEntity {
    return DatesEntity(
        this.maximum,
        this.minimum
    )
}

internal fun MovieDto.toEntity(category: String? = null): MovieEntity {
    return MovieEntity(
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
        category = category,
        isFavorite = false
    )
}


internal fun NowPlayingMoviesDto.toEntity(): NowPlayingMoviesEntity {
    return NowPlayingMoviesEntity(
        this.dates?.toEntity(),
        this.page,
        this.movies?.map { it.toEntity() },
        this.totalPages,
        this.totalResults
    )
}

internal fun PopularMoviesDto.toEntity(): PopularMoviesEntity {
    return PopularMoviesEntity(
        this.page,
        this.movies?.map { it.toEntity() },
        this.totalPages,
        this.totalResults
    )
}

internal fun TrendingMoviesDto.toEntity(): TrendingMoviesEntity {
    return TrendingMoviesEntity(
        this.page,
        this.movies?.map { it.toEntity() },
        this.totalPages,
        this.totalResults
    )
}

internal fun UpcomingMoviesDto.toEntity(): UpcomingMoviesEntity {
    return UpcomingMoviesEntity(
        this.dates?.toEntity(),
        this.page,
        this.movies?.map { it.toEntity() },
        this.totalPages,
        this.totalResults
    )
}

internal fun GenreDto.toEntity(): GenreEntity {
    return GenreEntity(
        this.id,
        this.name
    )
}

internal fun SpokenLanguageDto.toEntity(): SpokenLanguageEntity {
    return SpokenLanguageEntity(
        this.englishName,
        this.iso6391,
        this.name
    )
}

internal fun MovieDetailsDto.toEntity(): MovieDetailsEntity {
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

internal fun ActorDto.toEntity(): ActorEntity {
    return ActorEntity(
        this.castId,
        this.character,
        this.creditId,
        this.id,
        this.name,
        this.originalName,
        this?.profilePath
    )
}

internal fun CastDto.toEntity(): CastEntity {
    return CastEntity(
        this.actor?.map { it.toEntity() },
        this.id
    )
}

internal fun VideoDto.toEntity(): VideoEntity {
    return VideoEntity(
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

internal fun MovieVideoDto.toEntity(): MovieVideoEntity {
    return MovieVideoEntity(
        this.id,
        this.videos?.map { it.toEntity() }
    )
}

internal fun SimilarMoviesDto.toEntity(): SimilarMoviesEntity {
    return SimilarMoviesEntity(
        this.page,
        this.movies?.map { it.toEntity() },
        this.totalPages,
        this.totalResults
    )
}