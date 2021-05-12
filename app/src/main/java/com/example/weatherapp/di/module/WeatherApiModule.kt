package com.example.weatherapp.di.module


import com.example.weatherapp.domain_layer.network.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class WeatherApiModule {

    @Provides
    @Singleton
    fun provideWeatherApiService(retrofitBuilder: Retrofit.Builder): WeatherApi {
        return retrofitBuilder.baseUrl(WeatherApi.BASE_URL).build().create(WeatherApi::class.java)
    }
}