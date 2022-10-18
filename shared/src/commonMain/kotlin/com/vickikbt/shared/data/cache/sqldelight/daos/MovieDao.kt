package com.vickikbt.shared.data.cache.sqldelight.daos

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.vickikbt.shared.data.cache.sqldelight.AppDatabase
import com.vickikbt.shared.data.cache.sqldelight.MovieDetailsEntity
import com.vickikbt.shared.data.cache.sqldelight.MovieEntity
import com.vickikbt.shared.domain.utils.DatabaseDriverFactory
import kotlinx.coroutines.flow.map

class MovieDao constructor(private val databaseDriverFactory: DatabaseDriverFactory) {

    private val appDatabase = AppDatabase(driver = databaseDriverFactory.createDriver())
    private val dbQuery = appDatabase.appDatabaseQueries

    /**Cache movies to database*/
    fun saveMovies(movies: List<MovieEntity>) {
        dbQuery.transaction {
            movies.forEach { movieEntity ->
                dbQuery.saveMovies(movieEntity)
            }
        }
    }

    /**Get cached movies from database*/
    fun getMoviesByCategory(category: String) =
        dbQuery.getMoviesByCategory(category = category).asFlow().map { it.executeAsList() }

    /**Cache favourite movie detail*/
    fun saveMovieDetails(movieDetailsEntity: MovieDetailsEntity) {
        dbQuery.transaction {
            dbQuery.saveMovieDetails(movieDetailsEntity)
        }
    }

    /**Get a list of cached favourite movie*/
    fun getFavouriteMovies() =
        dbQuery.getFavouriteMovies().asFlow().map { it.executeAsList() }

    /**Get cached movie details based on ID*/
    fun getMovieDetails(id: Int) =
        dbQuery.getMovieDetails(id = id).asFlow().map { it.executeAsOneOrNull() }

    fun deleteMovieDetails(id: Int) =
        dbQuery.transaction { dbQuery.deleteMovieDetails(id = id) }
}
