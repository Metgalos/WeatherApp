package com.example.weatherapp.presentation.screen.weather

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
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
        checkLocationPermissions()
    }

    private fun checkLocationPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(requireContext(), "Request last weather", Toast.LENGTH_SHORT)
                    .show()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                Toast.makeText(
                    requireContext(),
                    "Should show request permission",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {
                requestPermission.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }
    }
}