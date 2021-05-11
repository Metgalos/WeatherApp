package com.example.weatherapp.weatherstore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDatabaseViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    val getAll: LiveData<List<Weather>> = repository.getAll

    fun add(weather: Weather) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(weather)
        }
    }
}