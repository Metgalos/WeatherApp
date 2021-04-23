package com.example.weatherapp.data

import androidx.lifecycle.LiveData

class WeatherRepository(private val weatherDao: WeatherDao) {

    val getAll: LiveData<List<Weather>> = weatherDao.getAll()

    suspend fun add(weather: Weather) {
        weatherDao.add(weather)
    }
}