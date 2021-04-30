package com.example.weatherapp.weatherapi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.App
import com.example.weatherapp.fragments.search.WeatherFragmentNavigation
import com.example.weatherapp.infrastructure.viewmodel.Emitter
import com.example.weatherapp.weatherapi.models.Weather
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherApiViewModel (
    private val weatherApi: WeatherApi = App.appComponent.getWeatherApi()
): ViewModel() {
    val emitter = Emitter()

    val myResponse: MutableLiveData<Response<Weather>> = MutableLiveData()

    fun doOnGetCurrentWeatherButtonClick(city: String) {
        viewModelScope.launch {
            myResponse.value = weatherApi.getCurrentWeather(city)
            emitter.emitAndExecute(WeatherFragmentNavigation.GetCurrentWeather())
        }
    }
}