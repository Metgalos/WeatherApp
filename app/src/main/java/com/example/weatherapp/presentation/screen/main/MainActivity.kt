package com.example.weatherapp.presentation.screen.main

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.navHostFragment)
    }

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        binding.mainBottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search -> navController.navigate(R.id.action_global_weatherFragment)
                R.id.history -> navController.navigate(R.id.action_global_historyFragment)
                else -> return@setOnItemSelectedListener false
            }
            return@setOnItemSelectedListener true
        }
        return super.onCreateView(name, context, attrs)
    }
}