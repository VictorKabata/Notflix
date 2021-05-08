package com.vickikbt.data.cache.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.models.entity.CastEntity
import com.vickikbt.data.models.entity.CastItemEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CastDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var castDao: CastDao

    private val cast = CastItemEntity(
        true,
        1,
        "Character",
        "1",
        1,
        1,
        "Name",
        1,
        "Original name",
        1.0,
        "profile_url"
    )

    private val castEntity = CastEntity(listOf(cast), 1)



    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        castDao = appDatabase.castDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun saveMovieCastTest() = runBlocking {
        castDao.saveMovieCast(castEntity)

        val result = castDao.getMovieCast(1)

        assertThat(result).isEqualTo(castEntity)
    }

    @Test
    fun getMovieCastTest() = runBlocking {
        castDao.saveMovieCast(castEntity)

        val result = castDao.getMovieCast(1)

        assertThat(result).isEqualTo(castEntity)
    }

    @Test
    fun deleteAllMovieCastTest() = runBlocking {

        castDao.saveMovieCast(castEntity)
        castDao.deleteAllMovieCast()

        val result = castDao.getMovieCast(1)

        assertThat(result).isNull()
    }

}