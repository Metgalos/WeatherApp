package com.example.weatherapp.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentHistoryBinding
import com.example.weatherapp.weatherstore.WeatherDatabaseViewModel


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var dbViewModel: WeatherDatabaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_history, container, false)

        val adapter = WeatherListAdapter()
        val recyclerView = binding.historyRecycleView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        dbViewModel = ViewModelProvider(this).get(WeatherDatabaseViewModel::class.java)
        dbViewModel.getAll.observe(viewLifecycleOwner, Observer { weathers ->
            adapter.setData(weathers)
        })

        return binding.root
    }
}