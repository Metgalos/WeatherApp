package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeatherDao {

    @Query("SELECT * FROM responses_history ORDER BY response_datetime DESC")
    fun getAll(): LiveData<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(weather: Weather)
}