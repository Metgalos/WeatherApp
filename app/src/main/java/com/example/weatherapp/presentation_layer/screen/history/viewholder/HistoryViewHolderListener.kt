package com.example.weatherapp.presentation_layer.screen.history.viewholder

import com.example.weatherapp.data_layer.entity.WeatherEntity

interface HistoryViewHolderListener {
    fun onDeleteItem(weatherEntity: WeatherEntity)
}