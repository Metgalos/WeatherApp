package com.example.weatherapp.fragments.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.weatherstore.Weather
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.weatherapi.WeatherApiViewModel
import com.example.weatherapp.infrastructure.DateFormat
import com.example.weatherapp.infrastructure.image.GlideImageLoader
import com.example.weatherapp.infrastructure.image.LoadPhotoConfig
import com.example.weatherapp.infrastructure.viewmodel.EventObserver
import com.example.weatherapp.weatherstore.WeatherDatabaseViewModel
import java.text.SimpleDateFormat
import java.util.*


class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private val apiViewModel: WeatherApiViewModel by viewModels()
    private val dbViewModel: WeatherDatabaseViewModel by viewModels()
    private val navigationEventsObserver = EventObserver {event ->
        when (event) {
            is WeatherFragmentNavigation.GetCurrentWeather -> getCurrentWeather()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        apiViewModel.emitter.observe(viewLifecycleOwner, navigationEventsObserver)
        getCurrentWeatherButtonListeners()
        return binding.root
    }

    private fun getCurrentWeather() {
        val response = apiViewModel.myResponse.value

        response?.let {
            if (it.isSuccessful) {
                GlideImageLoader.load(
                    LoadPhotoConfig(it.body()?.current?.icons?.first().toString()),
                    binding.weatherIconImageView
                )

                binding.weather = it.body()
                binding.weatherDataLayout.visibility = View.VISIBLE
                insertWeatherHistory()
            } else {
                Log.d("Response", it.errorBody().toString())
                Toast.makeText(context, "Response error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun insertWeatherHistory() {
        val weatherBinding = binding.weather

        val weather = Weather(
            weatherBinding?.location?.name,
            weatherBinding?.current?.temperature,
            weatherBinding?.current?.feelslike,
            weatherBinding?.current?.icons?.first().toString(),
            SimpleDateFormat(DateFormat.DB_DATE_FORMAT, Locale.US).format(Date())
        )

        dbViewModel.add(weather)
    }

    private fun getCurrentWeatherButtonListeners() {
        binding.getCurrentWeatherButton.setOnClickListener {
            apiViewModel.doOnGetCurrentWeatherButtonClick(binding.enterCityEdit.text.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        activity?.currentFocus?.let { v ->
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }

        when (item.itemId) {
            R.id.historyMenuItem -> this.findNavController().navigate(
                R.id.action_weatherFragment_to_historyFragment
            )
        }

        return true
    }
}