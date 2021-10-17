package com.example.weatherapp.domain.model

data class Weather(
    val id: Int,
    val lat: Double,
    val lon: Double,
    val temperature: Double,
    val feelsLike: Double,
)