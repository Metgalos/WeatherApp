package com.example.weatherapp.presentation.screen.history.viewholder

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.FragmentHistoryBinding

class HistoryLoadStateHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = FragmentHistoryBinding.bind(itemView)

    fun bind(loadState: LoadState) {
        binding.progressBar.isVisible = (loadState is LoadState.Loading)
    }
}