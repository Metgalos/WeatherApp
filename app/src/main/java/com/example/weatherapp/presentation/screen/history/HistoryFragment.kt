package com.example.weatherapp.presentation.screen.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.WeatherEntity
import com.example.weatherapp.databinding.FragmentHistoryBinding
import com.example.weatherapp.data.database.viewmodel.WeatherDatabaseViewModel
import com.example.weatherapp.presentation.screen.history.adapter.HistoryLoaderStateAdapter
import com.example.weatherapp.presentation.screen.history.adapter.WeatherListAdapter
import com.example.weatherapp.presentation.screen.history.viewholder.HistoryViewHolderListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val dbViewModel: WeatherDatabaseViewModel by viewModels()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        WeatherListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentHistoryBinding.inflate()
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_history, container, false)

        setDeleteItemListener()

        binding.historyRecycleView.adapter = adapter.withLoadStateFooter(
            HistoryLoaderStateAdapter()
        )
        binding.historyRecycleView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            dbViewModel.history.collectLatest { adapter.submitData(it) }
        }

        return binding.root
    }

    private fun setDeleteItemListener() {
        adapter.setListener(object : HistoryViewHolderListener {
            override fun onDeleteItem(weatherEntity: WeatherEntity) {
                viewLifecycleOwner.lifecycleScope.launch {
                    dbViewModel.delete(weatherEntity)
                    adapter.deleteItem(weatherEntity)
                }
            }
        })
    }
}