package com.vickikbt.data.mappers

import com.vickikbt.data.models.entity.*
import com.vickikbt.domain.models.*

internal fun PopularResultEntity.toDomain(): PopularResult {
    return PopularResult(
        this.page,
        this.movieEntity.map { it.toDomain() },
        this.total_pages,
        this.total_results
    )
}

internal fun UpcomingResultEntity.toDomain(): UpcomingResult {
    return UpcomingResult(
        this.upcomingDates.toDomain(),
        this.page,
        this.moviesEntity.map { it.toDomain() },
        this.total_pages,
        this.total_results
    )
}

internal fun MovieEntity.toDomain(): Movie {
    return Movie(
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

internal fun UpcomingDatesEntity.toDomain(): UpcomingDates {
    return UpcomingDates(
        this.maximum,
        this.minimum
    )
}

internal fun MovieDetailsEntity.toDomain(): MovieDetails {
    return MovieDetails(
        this.adult,
        this.backdropPath,
        this.budget,
        this.genres.map { it.toDomain() },
        this.homepage,
        this.id,
        this.imdbId,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.productionCompanies.map { it.toDomain() },
        this.productionCountries.map { it.toDomain() },
        this.releaseDate,
        this.revenue,
        this.runtime,
        this.spokenLanguages.map { it.toDomain() },
        this.status,
        this.tagLine,
        this.title,
        this.video,
        this.voteAverage,
        this.voteCount
    )
}

internal fun GenreEntity.toDomain(): Genre {
    return Genre(
        this.id,
        this.name,
    )
}

internal fun ProductionCompanyEntity.toDomain(): ProductionCompany {
    return ProductionCompany(
        this.id,
        this.logoPath,
        this.name,
        this.originCountry
    )
}

internal fun ProductionCountryEntity.toDomain(): ProductionCountry {
    return ProductionCountry(
        this.iso31661,
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