package com.example.weatherapp.weatherapi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.weatherapi.models.Weather
import com.example.weatherapp.weatherapi.WeatherService
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherApiViewModel(private val weatherService: WeatherService): ViewModel() {

    val myResponse: MutableLiveData<Response<Weather>> = MutableLiveData()

    fun getCurrentWeather(city: String) {
        viewModelScope.launch {
            myResponse.value = weatherService.getCurrentWeather(city)
        }
    }
}