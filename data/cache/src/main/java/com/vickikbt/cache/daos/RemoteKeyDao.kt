package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.cache.models.RemoteKey

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRemoteKeys(remoteKeys: List<RemoteKey>)

    @Query("SELECT * FROM Remote_Key_Table WHERE Movie_ID=:movieId")
    suspend fun getRemoteKey(movieId: Int): RemoteKey

    @Query("DELETE FROM Remote_Key_Table")
    suspend fun deleteRemoteKeys()

}