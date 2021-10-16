package com.example.weatherapp.core.di

import com.example.weatherapp.data.storage.Storage
import com.example.weatherapp.data.storage.StorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    abstract fun bindsStorage(storageImpl: StorageImpl): Storage
}