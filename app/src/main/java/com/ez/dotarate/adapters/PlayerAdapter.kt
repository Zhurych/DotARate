package com.ez.dotarate.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ez.dotarate.R
import com.ez.dotarate.databinding.PlayerStatsItemBinding
import com.ez.dotarate.model.Player

class PlayerAdapter(private val listPlayers: ArrayList<Player>, private val maxCountBuff: Int) :
    RecyclerView.Adapter<PlayerAdapter.PlayerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<PlayerStatsItemBinding>(
            inflater,
            R.layout.player_stats_item,
            parent,
            false
        )

        return PlayerHolder(binding)
    }

    override fun getItemCount() = listPlayers.size

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        val gameDetail = listPlayers[position]

        holder.bind(gameDetail)
    }

    inner class PlayerHolder(private var binding: PlayerStatsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.player = player

            if (maxCountBuff == 3) binding.thirdBuffField.visibility = View.VISIBLE
            else if (maxCountBuff == 4)  binding.fourthBuffField.visibility = View.VISIBLE

            // Используется для того, что бы биндинг выполинлся как можно скорее
            binding.executePendingBindings()
        }
    }
}