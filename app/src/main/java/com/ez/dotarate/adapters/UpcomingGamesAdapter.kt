package com.ez.dotarate.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ez.dotarate.R
import com.ez.dotarate.databinding.UpcomingGamesItemBinding
import com.ez.dotarate.model.UpcomingGame

class UpcomingGamesAdapter : PagedListAdapter<UpcomingGame, UpcomingGamesAdapter.UpcomingGameHolder>(DIFF_CALLBACK) {

    private companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UpcomingGame>() {
            override fun areItemsTheSame(oldItem: UpcomingGame, newItem: UpcomingGame): Boolean {
                return oldItem.begin_at == newItem.begin_at
            }

            override fun areContentsTheSame(oldItem: UpcomingGame, newItem: UpcomingGame): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingGameHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<UpcomingGamesItemBinding>(
            inflater,
            R.layout.upcoming_games_item,
            parent,
            false
        )

        return UpcomingGameHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingGameHolder, position: Int) {
        Log.d("MyLogs", "UpcomingGamesAdapter. fun onBindViewHolder. ПОЗИЦИЯ = $position")
        val upcomingGame = getItem(position)

        if (upcomingGame != null) holder.bind(upcomingGame)
    }

    inner class UpcomingGameHolder(private val binding: UpcomingGamesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(upcomingGame: UpcomingGame) {
            binding.upcomingGame = upcomingGame

            binding.executePendingBindings()
        }
    }
}