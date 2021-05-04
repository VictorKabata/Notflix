package com.vickikbt.data.mappers

import com.vickikbt.data.models.dto.*
import com.vickikbt.data.models.entity.*

internal fun PopularResultDto.toEntity(): PopularResultEntity {
    return PopularResultEntity(
        this.page,
        this.movieDtos.map { it.toEntity() },
        this.total_pages,
        this.total_results
    )
}

internal fun UpcomingResultDto.toEntity(): UpcomingResultEntity {
    return UpcomingResultEntity(
        this.upcomingDates.toEntity(),
        this.page,
        this.moviesDto.map { it.toEntity() },
        this.total_pages,
        this.total_results
    )
}

internal fun MovieDto.toEntity(): MovieEntity {
    return MovieEntity(
        this.adult,
        this.backdrop_path,
        this.genre_ids,
        this.id,
        this.original_language,
        this.original_title,
        this.overview,
        this.popularity,
        this.poster_path,
        this.release_date,
        this.original_title,
        this.video,
        this.vote_average,
        this.vote_count,
    )
}

internal fun UpcomingDatesDto.toEntity(): UpcomingDatesEntity {
    return UpcomingDatesEntity(
        this.maximum,
        this.minimum
    )
}

internal fun MovieDetailsDto.toEntity(): MovieDetailsEntity {
    return MovieDetailsEntity(
        this.adult,
        this.backdropPath,
        this.budget,
        this.genreDtos?.map { it.toEntity() },
        this.homepage,
        this.id,
        this.imdbId,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.productionCompanyDtos?.map { it.toEntity() },
        this.productionCountryDtos?.map { it.toEntity() },
        this.releaseDate,
        this.revenue,
        this.runtime,
        this.spokenLanguageDtos?.map { it.toEntity() },
        this.status,
        this.tagline,
        this.title,
        this.video,
        this.voteAverage,
        this.voteCount
    )
}

internal fun GenreDto.toEntity(): GenreEntity {
    return GenreEntity(
        this.id,
        this.name,
    )
}

internal fun ProductionCompanyDto.toEntity(): ProductionCompanyEntity {
    return ProductionCompanyEntity(
        this.id,
        this.logoPath,
        this.name,
        this.originCountry
    )
}

internal fun ProductionCountryDto.toEntity(): ProductionCountryEntity {
    return ProductionCountryEntity(
        this.iso31661,
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

internal fun CastDto.toEntity(): CastEntity {
    return CastEntity(
        this.castItemDto.map { it.toEntity() },
        this.id
    )
}

internal fun CastItemDto.toEntity(): CastItemEntity {
    return CastItemEntity(
        this.adult,
        this.castId,
        this.character,
        this.creditId,
        this.gender,
        this.castId,
        this.name,
        this.order,
        this.originalName,
        this.popularity,
        this.profilePath
    )
}

internal fun VideoDto.toEntity(): VideoEntity {
    return VideoEntity(
        this.id,
        this.videoItemDtos.map { it.toEntity() }
    )
}

internal fun VideoItemDto.toEntity(): VideoItemEntity {
    return VideoItemEntity(
        this.id,
        this.iso_3166_1,
        this.iso_639_1,
        this.key,
        this.name,
        this.site,
        this.size,
        this.type
    )
}

internal fun SimilarResultDto.toEntity(): SimilarResultEntity {
    return SimilarResultEntity(
        this.page,
        this.movieDtos.map { it.toEntity() },
        this.total_pages,
        this.total_results
    )
}

