package com.example.weatherapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "responses_history")
data class Weather(
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "temperature") val temperature: Int?,
    @ColumnInfo(name = "feels_like") val feelslike: Int?,
    @ColumnInfo(name = "icon_url") val icon: String?,
    @ColumnInfo(name = "response_datetime") val responseDatetime: String?,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0
)