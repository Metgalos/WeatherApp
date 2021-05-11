package com.example.weatherapp.weatherapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.weatherapi.models.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherApiViewModel @Inject constructor(
    private val weatherApi: WeatherApi
): ViewModel() {
    private val _response = MutableLiveData<Response<Weather>>()
    val response: LiveData<Response<Weather>>
        get() {
            if (_responseEvent.value == true) {
                _responseEvent.value = false
            }
            return _response
        }

    private val _responseEvent = MutableLiveData<Boolean>()
    val responseEvent: LiveData<Boolean>
        get() = _responseEvent

    fun getCurrentWeather(city: String) {
        viewModelScope.launch() {
            _response.value = weatherApi.getCurrentWeather(city)
            _responseEvent.value = true
        }
    }
}