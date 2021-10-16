package com.example.weatherapp.presentation.screen.history.viewholder

import android.view.View
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.view.*

class HistoryLoadStateHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(loadState: LoadState) {
        itemView.progressBar.isVisible = (loadState is LoadState.Loading)
    }
}