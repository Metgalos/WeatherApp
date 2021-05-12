package com.example.weatherapp.domain_layer.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherapp.data_layer.entity.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM responses_history ORDER BY response_datetime DESC")
    fun getAll(): LiveData<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(weather: Weather)
}