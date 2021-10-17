package com.example.weatherapp.presentation.screen.history

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import com.example.weatherapp.data.entity.WeatherEntity
import com.example.weatherapp.databinding.FragmentHistoryBinding
import com.example.weatherapp.presentation.base.BaseFragment
import com.example.weatherapp.presentation.screen.history.adapter.HistoryLoaderStateAdapter
import com.example.weatherapp.presentation.screen.history.adapter.WeatherListAdapter
import com.example.weatherapp.presentation.screen.history.viewholder.HistoryViewHolderListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>(
    FragmentHistoryBinding::inflate,
) {

    private val viewModel: HistoryViewModel by viewModels()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        WeatherListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDeleteItemListener()

        binding.historyRecycleView.adapter = adapter.withLoadStateFooter(
            HistoryLoaderStateAdapter()
        )
        binding.historyRecycleView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            viewModel.history.collectLatest { adapter.submitData(it) }
        }
    }

    private fun setDeleteItemListener() {
        adapter.setListener(object : HistoryViewHolderListener {
            override fun onDeleteItem(weatherEntity: WeatherEntity) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.delete(weatherEntity)
                    adapter.deleteItem(weatherEntity)
                }
            }
        })
    }
}