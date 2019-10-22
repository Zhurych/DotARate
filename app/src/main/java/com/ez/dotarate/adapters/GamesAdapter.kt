package com.ez.dotarate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ez.dotarate.R
import com.ez.dotarate.database.Game
import com.ez.dotarate.databinding.GameListItemBinding

class GamesAdapter : PagedListAdapter<Game, GamesAdapter.GamesHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Game>() {

            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                // The ID property identifies when items are the same.
                return oldItem.match_id == newItem.match_id
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                // Don't use the "==" operator here. Either implement and use .equals(),
                // or write custom data comparison logic here.
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<GameListItemBinding>(
            inflater,
            R.layout.game_list_item,
            parent,
            false
        )
        return GamesHolder(binding)
    }

    override fun onBindViewHolder(holder: GamesHolder, position: Int) {
        val game = getItem(position)

        // Note that "game" can be null if it's a placeholder.
        if (game != null) holder.bind(game)
    }

    inner class GamesHolder(private var binding: GameListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(game: Game) {
            // Установка биндинга (передача в него объекта game)
            binding.game = game
            // Используется для того, что бы биндинг выполинлся как можно скорее
            binding.executePendingBindings()
        }
    }
}