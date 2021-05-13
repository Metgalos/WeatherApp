package com.example.weatherapp.domain_layer.network.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data_layer.response.Weather
import com.example.weatherapp.domain_layer.database.repository.WeatherRepository
import com.example.weatherapp.domain_layer.network.api.WeatherApi
import com.example.weatherapp.data_layer.response.Weather as WeatherResponse
import com.example.weatherapp.domain_layer.storage.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherApiViewModel @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherRepository: WeatherRepository,
    private val storage: Storage
): ViewModel() {

    private val _response = MutableLiveData<Response<Weather>>()
    val response: LiveData<Response<Weather>> = _response

    init {
        getLastWeather()
    }

    fun getCurrentWeather(city: String) {
        viewModelScope.launch() {
            val responseValue = weatherApi.getCurrentWeather(city)
            _response.value = responseValue
            saveLastWeather(responseValue)
        }
    }

    private fun getLastWeather() {
        viewModelScope.launch {
            storage.getLastCity()?.let { city -> getCurrentWeather(city) }
        }
    }

    private fun saveLastWeather(weatherResponse: Response<WeatherResponse>) {
        viewModelScope.launch {
            weatherResponse.body()?.let { weather ->
                if (weatherResponse.isSuccessful) {
                    weatherRepository.addFromRequest(weather)
                    weather.location?.name?.let { city ->
                        storage.saveLastCity(city) }
                }
            }
        }
    }
}