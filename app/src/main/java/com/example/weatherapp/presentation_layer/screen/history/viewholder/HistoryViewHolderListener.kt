package com.example.weatherapp.presentation_layer.screen.history.viewholder

import com.example.weatherapp.data_layer.entity.Weather

interface HistoryViewHolderListener {
    fun onDeleteItem(weather: Weather)
}