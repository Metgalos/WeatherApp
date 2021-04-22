package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name") var name: String,
    @SerializedName("country") var country: String
)