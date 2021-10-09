package com.example.weatherapp.domain_layer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.data_layer.entity.WeatherEntity
import com.example.weatherapp.domain_layer.database.dao.WeatherDao

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        const val DB_NAME = "weather_database"

        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val instance = instance

            if (instance != null) {
                return instance
            }

            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()

                Companion.instance = newInstance

                return newInstance
            }
        }
    }

}