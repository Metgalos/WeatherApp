package com.example.weatherapp.infrastructure.di


import com.example.weatherapp.weatherapi.WeatherApi
import com.example.weatherapp.weatherapi.viewmodel.WeatherApiViewModel
import com.example.weatherapp.weatherapi.viewmodel.WeatherViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class WeatherApiModule {

    @Provides
    @Singleton
    fun provideWeatherApiService(retrofitBuilder: Retrofit.Builder): WeatherApi {
        return retrofitBuilder.baseUrl(WeatherApi.BASE_URL).build().create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherApiViewModel(weatherApi: WeatherApi): WeatherApiViewModel {
        return WeatherViewModelFactory(weatherApi).create(WeatherApiViewModel::class.java)
    }
}