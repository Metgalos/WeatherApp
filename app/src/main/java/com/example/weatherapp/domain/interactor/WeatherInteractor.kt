package com.example.weatherapp.domain.interactor

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherInteractor @Inject constructor(
    private val repository: WeatherRepository,
) {

    suspend fun getCurrentWeather(city: String): Weather {
        val weather = repository.getCurrent(city)
        repository.save(weather)
        return weather
    }
}