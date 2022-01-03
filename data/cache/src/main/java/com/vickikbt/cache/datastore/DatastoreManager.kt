package com.vickikbt.cache.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreManager constructor(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "notflix_datastore")

    suspend fun saveString(key: String, value: String) {
        val dsKey = stringPreferencesKey(key)

        context.dataStore.edit { preference ->
            preference[dsKey] = value
        }
    }

    fun getString(key: String, defaultValue: String): Flow<String> {
        val dsKey = stringPreferencesKey(key)

        return context.dataStore.data.map { preferences ->
            preferences[dsKey] ?: defaultValue
        }
    }

    suspend fun saveLong(key: String, value: Long) {
        val dsKey = longPreferencesKey(key)

        context.dataStore.edit { preference ->
            preference[dsKey] = value
        }
    }

    suspend fun getLong(key: String, defaultValue: Long): Flow<Long> {
        val dsKey = longPreferencesKey(key)

        return context.dataStore.data.map { preferences ->
            preferences[dsKey] ?: defaultValue
        }
    }

}