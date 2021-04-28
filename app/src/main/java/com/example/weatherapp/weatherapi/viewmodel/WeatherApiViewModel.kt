package com.example.weatherapp.weatherapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.weatherapi.WeatherApi
import com.example.weatherapp.weatherapi.models.Weather
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class WeatherApiViewModel @Inject constructor(private val weatherApi: WeatherApi): ViewModel() {

    val myResponse: MutableLiveData<Response<Weather>> = MutableLiveData()

    fun getCurrentWeather(city: String) {
        viewModelScope.launch {
            myResponse.value = weatherApi.getCurrentWeather(city)
        }
    }
}