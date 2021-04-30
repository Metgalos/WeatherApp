package com.example.weatherapp.infrastructure.di

import com.example.weatherapp.weatherapi.WeatherApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, WeatherApiModule::class])
interface ApplicationComponent {
    fun getWeatherApi(): WeatherApi
}