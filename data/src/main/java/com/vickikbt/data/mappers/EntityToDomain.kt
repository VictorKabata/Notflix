package com.vickikbt.data.mappers


import com.vickikbt.data.models.entity.*
import com.vickikbt.domain.models.*

internal fun PopularResultEntity.toDomain(): PopularResult {
    return PopularResult(
        this.page,
        this.movieEntity?.map { it.toDomain() },
        this.total_pages,
        this.total_results
    )
}

internal fun UpcomingResultEntity.toDomain(): UpcomingResult {
    return UpcomingResult(
        this.upcomingDates?.toDomain(),
        this.page,
        this.moviesEntity?.map { it.toDomain() },
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
        this.genresEntity?.map { it.toDomain() },
        this.homepage,
        this.id,
        this.imdbId,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.productionCompaniesEntity?.map { it.toDomain() },
        this.productionCountriesEntity?.map { it.toDomain() },
        this.releaseDate,
        this.revenue,
        this.runtime,
        this.spokenLanguagesEntity?.map { it.toDomain() },
        this.status,
        this.tagline,
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
        this.logo_path,
        this.name,
        this.origin_country
    )
}

internal fun ProductionCountryEntity.toDomain(): ProductionCountry {
    return ProductionCountry(
        this.iso_3166_1,
        this.name
    )
}

internal fun SpokenLanguageEntity.toDomain(): SpokenLanguage {
    return SpokenLanguage(
        this.english_name,
        this.iso_639_1,
        this.name
    )
}

internal fun CastEntity.toDomain(): Cast {
    return Cast(
        this.cast?.map { it.toDomain() },
        this.id
    )
}

internal fun CastItemEntity.toDomain(): CastItem {
    return CastItem(
        this.adult,
        this.cast_id,
        this.character,
        this.credit_id,
        this.gender,
        this.cast_id,
        this.name,
        this.order,
        this.original_name,
        this.popularity,
        this.profile_path
    )
}

internal fun VideoEntity.toDomain(): Video {
    return Video(
        this.id,
        this.videoItemsEntity?.map { it.toDomain() }
    )
}

internal fun VideoItemEntity.toDomain(): VideoItem {
    return VideoItem(
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

internal fun SimilarResultEntity.toDomain(): SimilarResult {
    return SimilarResult(
        this.page,
        this.movieEntity?.map { it.toDomain() },
        this.total_pages,
        this.total_results
    )
}
