package com.example.weatherapp.presentation.screen.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.weatherapp.data.model.LoadPhotoConfig
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate,
) {

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setResponseObserver()
        setCurrentWeatherButtonListeners()
    }

    private fun setResponseObserver() {
        apiViewModel.response.observe(viewLifecycleOwner, {
            if (it.isSuccessful) {
                GlideImageLoader.load(
                    LoadPhotoConfig(it.body()?.current?.icons?.first().toString()),
                    binding.weatherIconImageView
                )
                binding.enterCityEdit.setText(it.body()?.location?.name)
                binding.weather = it.body()
                binding.weatherDataLayout.visibility = View.VISIBLE
            } else {
                Log.d("Response", it.errorBody().toString())
                Toast.makeText(context, "Response error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setCurrentWeatherButtonListeners() {
        binding.getCurrentWeatherButton.setOnClickListener {
            apiViewModel.getCurrentWeather(binding.enterCityEdit.text.toString())
        }
    }
}