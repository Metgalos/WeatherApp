package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.entity.WeatherEntity
import com.example.weatherapp.data.response.WeatherResponse
import com.example.weatherapp.domain.model.Weather
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    // TODO: implement method
    fun mapWeather(response: WeatherResponse): Weather {
        return Weather()
    }

    // TODO: implement method
    fun mapEntity(weather: Weather): WeatherEntity {
        return WeatherEntity()
    }
}