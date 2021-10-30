package com.example.weatherapp.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(weatherEntity: WeatherEntity)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clear()

    @Delete
    suspend fun delete(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM $TABLE_NAME ORDER BY response_datetime DESC")
    fun getAllPaged(): PagingSource<Int, WeatherEntity>

    @Query("SELECT * FROM $TABLE_NAME ORDER BY response_datetime DESC LIMIT :pageSize OFFSET (:page - 1) * :pageSize")
    suspend fun getAll(page: Int, pageSize: Int): List<WeatherEntity>

    companion object {
        private const val TABLE_NAME = "responses_history"
    }
}