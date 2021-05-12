package com.example.weatherapp.di.module

import android.content.Context
import com.example.weatherapp.domain_layer.storage.StorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideStorage(@ApplicationContext context: Context): StorageImpl = StorageImpl(context)
}