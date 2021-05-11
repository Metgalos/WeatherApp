package com.example.weatherapp

import android.app.Application
//import com.example.weatherapp.infrastructure.di.ApplicationComponent
//import com.example.weatherapp.infrastructure.di.DaggerApplicationComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

//    companion object {
//        val appComponent: ApplicationComponent by lazy {
//            DaggerApplicationComponent.builder().build()
//        }
//    }
}