package com.example.weatherapp.data.response

data class WeatherResponse(
    val base: String?,
    val cloudsResponse: CloudsResponse?,
    val cod: Int?,
    val coordResponse: CoordResponse?,
    val dt: Int?,
    val id: Int?,
    val measureResponse: MeasureWeatherResponse?,
    val name: String?,
    val sys: SysWeatherResponse?,
    val timezone: Int?,
    val visibility: Int?,
    val mainWeatherResponse: List<MainWeatherResponse>?,
    val wind: WindResponse?
)