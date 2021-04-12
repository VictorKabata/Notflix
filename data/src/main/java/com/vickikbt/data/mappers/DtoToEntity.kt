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
        this.genres.map { it.toEntity() },
        this.homepage,
        this.id,
        this.imdbId,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.productionCompanies.map { it.toEntity() },
        this.productionCountries.map { it.toEntity() },
        this.releaseDate,
        this.revenue,
        this.runtime,
        this.spokenLanguages.map { it.toEntity() },
        this.status,
        this.tagLine,
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

