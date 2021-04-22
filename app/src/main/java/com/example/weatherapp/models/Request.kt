package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("type") var type: String?,
    @SerializedName("query") var query: String?,
    @SerializedName("language") var language: String?,
    @SerializedName("unit") var unit: String?
)