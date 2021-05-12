package com.example.weatherapp.di.module

import com.example.weatherapp.domain_layer.storage.Storage
import com.example.weatherapp.domain_layer.storage.StorageImpl
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