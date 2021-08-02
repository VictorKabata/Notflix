package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vickikbt.cache.models.CastEntity

@Dao
interface CastDao {

    @Insert
    suspend fun saveMovieCast(castEntity: CastEntity)

    //TODO: Change this query to return Flow<CastEntity>?
    @Query("SELECT * FROM `Casts Table` WHERE id=:movieId")
    suspend fun getMovieCast(movieId: Int): CastEntity?

    //TODO: setup WorkManager to delete movie cast after 30 days
    @Query("DELETE FROM `Casts Table`")
    suspend fun deleteAllMovieCast()

}