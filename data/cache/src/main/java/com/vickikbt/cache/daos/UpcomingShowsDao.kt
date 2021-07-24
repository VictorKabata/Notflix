package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.cache.models.UpcomingResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUpcomingShows(upcomingResultEntity: UpcomingResultEntity)

    @Query("SELECT * FROM Upcoming_Result_Table")
    fun getUpcomingShows(): Flow<UpcomingResultEntity>

    @Query("DELETE FROM Upcoming_Result_Table")
    suspend fun deleteUpcomingShows()

    @Query("SELECT COUNT(*) FROM Upcoming_Result_Table")
    suspend fun isUpcomingCacheAvailable(): Int
}