package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.Weather

interface WeatherRepository {

    suspend fun getCurrent(city: String): Weather

    suspend fun save(weather: Weather)
}