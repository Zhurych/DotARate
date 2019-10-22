package com.ez.dotarate.view.fragments

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ez.dotarate.R
import com.ez.dotarate.adapters.PlayerAdapter
import com.ez.dotarate.constants.MATCH_ID_KEY
import com.ez.dotarate.databinding.FragmentGameDetailBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.GameDetailViewModel

class GameDetailFragment : BaseFragment<GameDetailViewModel, FragmentGameDetailBinding>() {
    override fun layout() = R.layout.fragment_game_detail

    override fun afterCreateView(view: View) {
        val matchId = arguments!!.getLong(MATCH_ID_KEY)
        Log.d("MyLogs", "МАТЧ ID = $matchId")

        vb.vm = vm

        vm.getGameDetail(matchId)

        // Need to set LayoutManager
        val recyclerViewRadiant = vb.rvGameFragmentRadiant
        recyclerViewRadiant.layoutManager = LinearLayoutManager(activity)

        // Need to set LayoutManager
        val recyclerViewDire = vb.rvGameFragmentDire
        recyclerViewDire.layoutManager = LinearLayoutManager(activity)

        var maxCountBuff = 0

        vm.liveGame.observe(this, Observer {

            Log.d("MyLogs", "ОТДЕЛЬНАЯ ИГРА. ПРИШЛИ ДАННЫЕ В ЛАЙВДАТА = $it")
            if (it != null) {
                vm.isLoaded.set(true)

                // Вычисление максимального количества бафов
                for (player in it.players) {
                    val buffs = player.permanent_buffs
                    @Suppress("SENSELESS_COMPARISON")
                    if (buffs != null) {
                        if (maxCountBuff < player.permanent_buffs.size) maxCountBuff =
                            player.permanent_buffs.size
                    }
                }

                if (it.radiant_win) vb.radiantWinnerTextView.visibility = View.VISIBLE
                else vb.direWinnerTextView.visibility = View.VISIBLE

                vb.gameDetail = it

                recyclerViewRadiant.adapter = PlayerAdapter(
                    arrayListOf(
                        it.players[0],
                        it.players[1],
                        it.players[2],
                        it.players[3],
                        it.players[4]
                    ), maxCountBuff
                )

                recyclerViewDire.adapter = PlayerAdapter(
                    arrayListOf(
                        it.players[5],
                        it.players[6],
                        it.players[7],
                        it.players[8],
                        it.players[9]
                    ), maxCountBuff
                )
            } else Toast.makeText(
                activity,
                "Пустой ответ. gameDetail = $it",
                Toast.LENGTH_LONG
            ).show()
        })

        vm.errorLiveData.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }
}