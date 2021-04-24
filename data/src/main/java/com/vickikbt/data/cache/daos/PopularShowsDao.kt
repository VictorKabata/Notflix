package com.vickikbt.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.data.models.entity.PopularResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePopularShows(popularResultEntity: PopularResultEntity)

    @Query("SELECT * FROM Popular_Result_Table")
    fun getPopularShows(): Flow<PopularResultEntity>

    @Query("DELETE FROM Popular_Result_Table")
    suspend fun deletePopularShows()

    @Query("SELECT COUNT(*) FROM Popular_Result_Table")
    suspend fun isPopularCacheAvailable(): Int
}