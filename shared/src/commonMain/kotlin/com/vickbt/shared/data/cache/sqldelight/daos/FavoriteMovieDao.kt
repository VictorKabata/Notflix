package com.vickbt.shared.data.cache.sqldelight.daos

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.vickbt.shared.AppDatabase
import com.vickbt.shared.domain.models.MovieDetails
import com.vickbt.shared.utils.DatabaseDriverFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

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
                status = movie.status,
                title = movie.title,
                voteAverage = movie.voteAverage,
                voteCount = movie.voteCount?.toLong()
            )
        }
    }

    /**Returns a list of all movie details in FavoriteMovie table*/
    fun getAllFavoriteMovies() = dbQuery.getAllFavoriteMovies().asFlow().mapToList(Dispatchers.IO)

    /**Delete favorite movie details based on its ID*/
    fun deleteFavouriteMovie(id: Int) = dbQuery.deleteFavoriteMovie(id = id.toLong())

    /**Delete all favorite movie details in FavoriteMovie table*/
    fun deleteAllFavouriteMovies() = dbQuery.deleteAllFavoriteMovies()

    /**Return value depending on whether movie details is in FavoriteMovie table*/
    fun isMovieFavorite(id: Int) = dbQuery.isMovieFavorite(id = id.toLong()).executeAsOneOrNull()
}
