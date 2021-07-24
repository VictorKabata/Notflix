package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.cache.models.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)

    @Query("SELECT * FROM `Favorite Movies Table`")
    fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

}