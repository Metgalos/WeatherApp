package com.example.weatherapp.weatherstore

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var instance: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase {
            val instance = instance

            if (instance != null) {
                return instance
            }

            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                ).build()

                Companion.instance = newInstance

                return newInstance
            }
        }
    }

}