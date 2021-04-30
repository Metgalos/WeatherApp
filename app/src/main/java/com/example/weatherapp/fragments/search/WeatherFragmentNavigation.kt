package com.example.weatherapp.fragments.search

import com.example.weatherapp.infrastructure.viewmodel.NavigationEvent

interface WeatherFragmentNavigation {
    class GetCurrentWeather() : NavigationEvent()
}