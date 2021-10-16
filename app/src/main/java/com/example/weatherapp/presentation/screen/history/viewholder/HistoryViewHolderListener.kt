package com.example.weatherapp.presentation.screen.history.viewholder

import com.example.weatherapp.data.entity.WeatherEntity

interface HistoryViewHolderListener {
    fun onDeleteItem(weatherEntity: WeatherEntity)
}