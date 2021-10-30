package com.example.weatherapp.core.di

import com.example.weatherapp.data.database.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Binds
    fun bindWeatherRepository(repositoryImpl: WeatherRepositoryImpl): WeatherRepository
}