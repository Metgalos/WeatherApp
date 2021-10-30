package com.example.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.database.dao.WeatherDao
import com.example.weatherapp.data.entity.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        const val DB_NAME = "weather_database"
    }
}