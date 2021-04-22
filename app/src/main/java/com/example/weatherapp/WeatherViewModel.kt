package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.Weather
import com.example.weatherapp.repository.Repository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Weather> = MutableLiveData()

    fun getCurrentWeather(city: String) {
        viewModelScope.launch {
            myResponse.value = repository.getCurrentWeather(city)
        }
    }
}