package com.example.weatherapp.domain_layer.database.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.data_layer.entity.Weather
import com.example.weatherapp.domain_layer.database.dao.WeatherDao
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherDao: WeatherDao) {

    val getAll: LiveData<List<Weather>> = weatherDao.getAll()

    suspend fun add(weather: Weather) {
        weatherDao.add(weather)
    }
}