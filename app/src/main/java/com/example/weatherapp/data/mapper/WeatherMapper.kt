package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.entity.WeatherEntity
import com.example.weatherapp.data.response.WeatherResponse
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.extensions.emptyIfNull
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun mapWeather(response: WeatherResponse): Weather {
        return Weather(
            id = response.id.emptyIfNull(),
            lat = response.coordResponse?.lat.emptyIfNull(),
            lon = response.coordResponse?.lon.emptyIfNull(),
            temperature = response.measureResponse?.temp.emptyIfNull(),
            feelsLike = response.measureResponse?.feelsLike.emptyIfNull(),
            location = response.name.emptyIfNull(),
            icon = response.mainWeatherResponse?.firstOrNull()?.icon.emptyIfNull(),
        )
    }

    fun mapEntity(weather: Weather): WeatherEntity {
        return WeatherEntity(
            location = weather.location,
            temperature = weather.temperature,
            feelslike = weather.feelsLike,
            icon = weather.icon,
            timestamp = System.currentTimeMillis(),
        )
    }
}