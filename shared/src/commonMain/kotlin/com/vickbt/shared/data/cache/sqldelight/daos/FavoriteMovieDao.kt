package com.vickbt.shared.data.cache.sqldelight.daos

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.vickbt.shared.data.cache.sqldelight.AppDatabase
import com.vickbt.shared.domain.models.MovieDetails
import com.vickbt.shared.utils.DatabaseDriverFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class FavoriteMovieDao(private val databaseDriverFactory: DatabaseDriverFactory) {
    val appDatabase = AppDatabase(driver = databaseDriverFactory.createDriver())
    val dbQuery = appDatabase.appDatabaseQueries

    /**Save movie details in FavoriteMovie table*/
    fun saveFavoriteMovie(movie: MovieDetails) {
        dbQuery.transaction {
            dbQuery.insertFavoriteMovie(
                id = movie.id.toLong(),
                imdbId = movie.imdbId,
                backdropPath = movie.backdropPath,
                posterPath = movie.posterPath,
                originalLanguage = movie.originalLanguage,
                originalTitle = movie.originalTitle,
                overview = movie.overview,
                popularity = movie.popularity,
                releaseDate = movie.releaseDate,
                runTime = movie.runtime?.toLong(),
                status = movie.status,
                tagLine = movie.tagline,
                title = movie.title,
                voteAverage = movie.voteAverage,
                voteCount = movie.voteCount?.toLong(),
                createdAt =
                    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                        .toString(),
            )
        }
    }

    /**Returns a list of all movie details in FavoriteMovie table*/
    fun getAllFavoriteMovies() = dbQuery.getAllFavoriteMovies().asFlow().mapToList(Dispatchers.IO)

    /**Get single movies from FavoriteMovie table based on its ID*/
    fun getFavoriteMovie(movieId: Int) = dbQuery.getFavoriteMovie(id = movieId.toLong()).executeAsOne()

    /**Delete favorite movie details based on its ID*/
    fun deleteFavouriteMovie(movieId: Int) = dbQuery.deleteFavoriteMovie(id = movieId.toLong())

    /**Delete all favorite movie details in FavoriteMovie table*/
    fun deleteAllFavouriteMovies() = dbQuery.deleteAllFavoriteMovies()

    /**Return value depending on whether movie details is in FavoriteMovie table*/
    fun isMovieFavorite(movieId: Int) = dbQuery.isMovieFavorite(id = movieId.toLong()).executeAsOneOrNull()
}
