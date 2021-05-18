package com.example.weatherapp.presentation_layer.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = findNavController(R.id.navHostFragment)

        binding.mainBottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search -> {
                    navController.navigate(R.id.action_global_weatherFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.history -> {
                    navController.navigate(R.id.action_global_historyFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }
}