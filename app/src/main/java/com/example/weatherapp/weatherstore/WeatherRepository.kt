package com.example.weatherapp.weatherstore

import androidx.lifecycle.LiveData
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherDao: WeatherDao) {

    val getAll: LiveData<List<Weather>> = weatherDao.getAll()

    suspend fun add(weather: Weather) {
        weatherDao.add(weather)
    }
}