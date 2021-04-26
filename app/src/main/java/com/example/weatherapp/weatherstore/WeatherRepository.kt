package com.example.weatherapp.weatherstore

import androidx.lifecycle.LiveData
import com.example.weatherapp.weatherstore.Weather
import com.example.weatherapp.weatherstore.WeatherDao

class WeatherRepository(private val weatherDao: WeatherDao) {

    val getAll: LiveData<List<Weather>> = weatherDao.getAll()

    suspend fun add(weather: Weather) {
        weatherDao.add(weather)
    }
}