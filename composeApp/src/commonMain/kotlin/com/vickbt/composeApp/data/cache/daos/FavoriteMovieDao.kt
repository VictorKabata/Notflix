package com.vickbt.composeApp.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vickbt.composeApp.data.cache.entities.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Insert
    suspend fun saveFavoriteMovie(movie: MovieDetailsEntity)

    @Query("SELECT * FROM `Favorite Movies`")
    fun getAllFavoriteMovies(): Flow<List<MovieDetailsEntity>?>

    @Query("SELECT * FROM `Favorite Movies` WHERE id = :id")
    fun getFavoriteMovie(id: Int): Flow<MovieDetailsEntity?>

    @Query("DELETE FROM `Favorite Movies` WHERE id = :id")
    suspend fun deleteFavoriteMovie(id: Int)

    @Query("DELETE FROM `Favorite Movies`")
    suspend fun deleteAllFavoriteMovies()

    @Query("SELECT COUNT() FROM `Favorite Movies` WHERE id = :id")
    fun isMovieFavorite(id: Int): Flow<Boolean?>
}
