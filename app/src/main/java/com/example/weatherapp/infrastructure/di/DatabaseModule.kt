package com.example.weatherapp.infrastructure.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.AppDatabase
import com.example.weatherapp.weatherstore.WeatherDao
import com.example.weatherapp.weatherstore.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DB_NAME
    ).build()

    @Provides
    fun provideWeatherDao(appDatabase: AppDatabase): WeatherDao = appDatabase.weatherDao()

    @Provides
    fun provideWeatherRepository(weatherDao: WeatherDao): WeatherRepository = WeatherRepository(weatherDao)
}