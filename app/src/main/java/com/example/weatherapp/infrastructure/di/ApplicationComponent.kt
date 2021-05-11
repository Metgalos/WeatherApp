//package com.example.weatherapp.infrastructure.di
//
//import com.example.weatherapp.weatherapi.WeatherApi
//import com.example.weatherapp.weatherstore.WeatherRepository
//import dagger.Component
//import javax.inject.Singleton
//
//@Singleton
//@Component(modules = [DatabaseModule::class, NetworkModule::class, WeatherApiModule::class])
//interface ApplicationComponent {
//    fun getWeatherApi(): WeatherApi
//    fun getWeatherRepository(): WeatherRepository
//}