package com.example.weatherapp.weatherstore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.weatherstore.Weather
import com.example.weatherapp.weatherstore.WeatherDatabase
import com.example.weatherapp.weatherstore.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherDatabaseViewModel(application: Application): AndroidViewModel(application) {

    val getAll: LiveData<List<Weather>>
    private val repository: WeatherRepository

    init {
        val weatherDao = WeatherDatabase.getDatabase(
            application
        ).weatherDao()
        repository =
            WeatherRepository(
                weatherDao
            )
        getAll = repository.getAll
    }

    fun add(weather: Weather) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(weather)
        }
    }
}