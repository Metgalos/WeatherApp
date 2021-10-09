package com.example.weatherapp.data_layer.response

data class MeasureWeatherResponse(
    val feels_like: Double?,
    val humidity: Int?,
    val pressure: Int?,
    val temp: Double?,
    val temp_max: Double?,
    val temp_min: Double?,
)