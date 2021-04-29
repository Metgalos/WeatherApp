package com.example.weatherapp.infrastructure.di

import com.example.weatherapp.weatherapi.viewmodel.WeatherApiViewModel
import com.example.weatherapp.weatherapi.viewmodel.WeatherApiViewModel_Factory
import com.example.weatherapp.weatherapi.viewmodel.WeatherViewModelFactory
import com.example.weatherapp.weatherstore.viewmodel.WeatherDatabaseViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, WeatherApiModule::class])
interface ApplicationComponent {

    fun getWeatherViewModelFactory(): WeatherViewModelFactory
}