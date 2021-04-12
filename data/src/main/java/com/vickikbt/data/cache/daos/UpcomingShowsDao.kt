package com.vickikbt.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.data.models.entity.PopularResultEntity
import com.vickikbt.data.models.entity.UpcomingResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUpcomingShows(upcomingResultEntity: UpcomingResultEntity)

    @Query("SELECT * FROM `Upcoming Result Table`")
    fun getUpcomingShows(): Flow<UpcomingResultEntity>

    @Query("DELETE FROM `Upcoming Result Table`")
    suspend fun deleteUpcomingShows()

    @Query("SELECT COUNT(*) FROM `Upcoming Result Table`")
    suspend fun isUpcomingCacheAvailable(): Int
}