package com.example.weatherapp.presentation.screen.weather

import android.Manifest
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.presentation.base.BaseFragment

class WeatherFragment : BaseFragment<FragmentWeatherBinding>(
    FragmentWeatherBinding::inflate,
) {

    private val viewModel: WeatherViewModel by viewModels()

    private val locationListener = LocationListener {
        viewModel.getWeather(it.latitude, it.longitude)
    }

    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            val locationManager =
                requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener
            )
            Toast.makeText(requireContext(), "Location get", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(
                requireContext(),
                R.string.location_permission_description,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermission.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }
}