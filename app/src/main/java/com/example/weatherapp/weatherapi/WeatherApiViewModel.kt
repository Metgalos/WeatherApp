package com.example.weatherapp.weatherapi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.App
import com.example.weatherapp.fragments.search.WeatherFragmentNavigation
import com.example.weatherapp.infrastructure.viewmodel.Emitter
import com.example.weatherapp.weatherapi.models.Weather
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherApiViewModel @Inject constructor(
    private val weatherApi: WeatherApi
): ViewModel() {
    val emitter = Emitter()

    val myResponse: MutableLiveData<Response<Weather>> = MutableLiveData()

    fun doOnGetCurrentWeatherButtonClick(city: String) {
        viewModelScope.launch() {
            myResponse.value = weatherApi.getCurrentWeather(city)
            emitter.emitAndExecute(WeatherFragmentNavigation.GetCurrentWeather())
        }
    }
}