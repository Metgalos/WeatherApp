package com.example.weatherapp.weatherapi.models

import com.example.weatherapp.weatherapi.models.Current
import com.example.weatherapp.weatherapi.models.Location
import com.example.weatherapp.weatherapi.models.Request
import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("request") var request: Request?,
    @SerializedName("location") var location: Location?,
    @SerializedName("current") var current: Current?
)