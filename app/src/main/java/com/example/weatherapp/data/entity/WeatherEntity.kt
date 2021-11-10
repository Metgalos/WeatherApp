package com.example.weatherapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "responses_history")
data class WeatherEntity(
    @ColumnInfo(name = "location")
    val location: String?,

    @ColumnInfo(name = "temperature")
    val temperature: Double?,

    @ColumnInfo(name = "feels_like")
    val feelslike: Double?,

    @ColumnInfo(name = "icon_url")
    val icon: String?,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long?,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
)