package com.example.weatherapp.weatherstore.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherDatabaseViewModelFactory(
    private var application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherDatabaseViewModel(application) as T
    }

}