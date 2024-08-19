package com.vickbt.shared.data.cache.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickbt.shared.data.cache.room.entities.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovie: FavoriteMovieEntity)

    @Query("SELECT * FROM `Favorite Movie Table` ORDER BY createdAt DESC")
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM `Favorite Movie Table` WHERE id = :id")
    suspend fun getFavoriteMovie(id: Int): FavoriteMovieEntity?

    @Query("DELETE FROM `Favorite Movie Table` WHERE id = :id")
    suspend fun deleteFavoriteMovie(id: Int): Int

    @Query("DELETE FROM `Favorite Movie Table`")
    suspend fun deleteAllFavoriteMovies()

    @Query("SELECT count(1) FROM `Favorite Movie Table` WHERE id = :id")
    suspend fun isMovieFavorite(id: Int): Int
}

