package com.example.weatherapp.data_layer.response

data class SysWeatherResponse(
    val country: String?,
    val id: Int?,
    val message: Double?,
    val sunrise: Int?,
    val sunset: Int?,
    val type: Int?,
)