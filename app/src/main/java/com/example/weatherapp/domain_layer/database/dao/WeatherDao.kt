package com.example.weatherapp.domain_layer.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.*
import com.example.weatherapp.data_layer.entity.Weather

@Dao
interface WeatherDao {

    companion object {
        private const val TABLE_NAME = "responses_history"
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(weather: Weather)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clear()

    @Delete
    suspend fun delete(weather: Weather)

    @Query("SELECT * FROM $TABLE_NAME ORDER BY response_datetime DESC")
    fun getAllPaged(): PagingSource<Int, Weather>

    @Query("SELECT * FROM $TABLE_NAME ORDER BY response_datetime DESC LIMIT :pageSize OFFSET (:page - 1) * :pageSize")
    suspend fun getAll(page: Int, pageSize: Int): List<Weather>
}