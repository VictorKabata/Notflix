package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.cache.models.CastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieCast(castEntity: CastEntity)

    @Query("SELECT * FROM `Casts Table` WHERE id=:movieId")
    fun getMovieCast(movieId: Int): Flow<CastEntity>

    //TODO: setup WorkManager to delete movie cast after 30 days
    @Query("DELETE FROM `Casts Table`")
    suspend fun deleteAllMovieCast()

    @Query("SELECT COUNT(*) FROM `Casts Table` WHERE id=:movieId")
    suspend fun isMovieCastAvailable(movieId: Int):Int

}