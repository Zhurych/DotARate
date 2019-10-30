package com.ez.dotarate.view.fragments

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ez.dotarate.DividerItemDecoration
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

        activity!!.title = ""

        vb.vm = vm

        vm.getGameDetail(matchId)

        // Need to set LayoutManager
        val recyclerViewRadiant = vb.rvGameFragmentRadiant
        recyclerViewRadiant.layoutManager = LinearLayoutManager(activity)
        recyclerViewRadiant.addItemDecoration(DividerItemDecoration(activity!!))

        // Need to set LayoutManager
        val recyclerViewDire = vb.rvGameFragmentDire
        recyclerViewDire.layoutManager = LinearLayoutManager(activity)
        recyclerViewDire.addItemDecoration(DividerItemDecoration(activity!!))

        var maxCountBuff = 0
        var maxSupportItems = 0

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

                    val purchase = player.purchase
                    @Suppress("SENSELESS_COMPARISON")
                    if (purchase != null) {
                        var currentMaxCountSuppItems = 0
                        if (purchase.dust != null) currentMaxCountSuppItems++
                        if (purchase.smoke_of_deceit != null) currentMaxCountSuppItems++
                        if (purchase.ward_sentry != null) currentMaxCountSuppItems++
                        if (purchase.ward_observer != null) currentMaxCountSuppItems++
                        if (purchase.gem != null) currentMaxCountSuppItems++
                        if (maxSupportItems < currentMaxCountSuppItems) maxSupportItems =
                            currentMaxCountSuppItems
                    }
                }

                vb.gameDetail = it

                recyclerViewRadiant.adapter = PlayerAdapter(
                    arrayListOf(
                        it.players[0],
                        it.players[1],
                        it.players[2],
                        it.players[3],
                        it.players[4]
                    ), maxCountBuff, maxSupportItems
                )

                recyclerViewDire.adapter = PlayerAdapter(
                    arrayListOf(
                        it.players[5],
                        it.players[6],
                        it.players[7],
                        it.players[8],
                        it.players[9]
                    ), maxCountBuff, maxSupportItems
                )
            }
        })

        vm.errorLiveData.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onStart() {
        super.onStart()
        val hsv = vb.hsvGameDetail
        hsv.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            run {
                if (scrollX >= 523) {
                    vb.vGameDetailFirst.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorShadow))
                    vb.vGameDetailSecond.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorShadow))
                    vb.vGameDetailThird.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorShadow))
                    vb.vGameDetailFourth.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorShadow))
                    vb.vGameDetailFifth.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorShadow))
                    vb.vGameDetailSixth.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorShadow))
                    vb.vGameDetailSeventh.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorShadow))
                    vb.vGameDetailEighth.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorShadow))
                    vb.vGameDetailNinth.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorShadow))
                    vb.vGameDetailTenth.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorShadow))
                } else {
                    vb.vGameDetailFirst.setBackgroundColor(ContextCompat.getColor(context!!, R.color.whiteLight))
                    vb.vGameDetailSecond.setBackgroundColor(ContextCompat.getColor(context!!, R.color.whiteLight))
                    vb.vGameDetailThird.setBackgroundColor(ContextCompat.getColor(context!!, R.color.whiteLight))
                    vb.vGameDetailFourth.setBackgroundColor(ContextCompat.getColor(context!!, R.color.whiteLight))
                    vb.vGameDetailFifth.setBackgroundColor(ContextCompat.getColor(context!!, R.color.whiteLight))
                    vb.vGameDetailSixth.setBackgroundColor(ContextCompat.getColor(context!!, R.color.whiteLight))
                    vb.vGameDetailSeventh.setBackgroundColor(ContextCompat.getColor(context!!, R.color.whiteLight))
                    vb.vGameDetailEighth.setBackgroundColor(ContextCompat.getColor(context!!, R.color.whiteLight))
                    vb.vGameDetailNinth.setBackgroundColor(ContextCompat.getColor(context!!, R.color.whiteLight))
                    vb.vGameDetailTenth.setBackgroundColor(ContextCompat.getColor(context!!, R.color.whiteLight))
                }
            }
        }
    }
}