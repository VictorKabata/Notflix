package com.vickikbt.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vickikbt.data.models.entity.CastEntity

@Dao
interface CastDao {

    @Insert
    suspend fun saveMovieCast(castEntity: CastEntity)

    //TODO: Change this query to return Flow<CastEntity>?
    @Query("SELECT * FROM Cast_Table WHERE ID=:movieId")
    suspend fun getMovieCast(movieId: Int): CastEntity?

    //TODO: setup WorkManager to delete movie cast after 30 days
    @Query("DELETE FROM Cast_Table")
    suspend fun deleteAllMovieCast()

}