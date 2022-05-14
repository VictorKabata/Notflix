package com.vickikbt.shared.data.cache.sqldelight.daos

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.vickikbt.shared.data.cache.sqldelight.AppDatabase
import com.vickikbt.shared.data.cache.sqldelight.MovieDetailsEntity
import com.vickikbt.shared.domain.utils.DatabaseDriverFactory
import kotlinx.coroutines.flow.map

class MovieDetailsDao constructor(private val databaseDriverFactory: DatabaseDriverFactory) {

    private val appDatabase = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = appDatabase.appDatabaseQueries

    fun saveMovieDetails(movieDetailsEntity: MovieDetailsEntity) {
        dbQuery.transaction {
            dbQuery.saveMovieDetails(movieDetailsEntity)
        }
    }

    fun getMovieDetails(movieId: Int) =
        dbQuery.getMovieDetails(id = movieId).asFlow().map { it.executeAsOneOrNull() }

    fun deleteMovieDetails() = dbQuery.transaction {
        dbQuery.deleteAllMovieDetais()
    }

    fun isMovieDetailsAvailable(movieId: Int) = dbQuery.isMovieDetailsAvailable(id = movieId).executeAsOneOrNull()
}
