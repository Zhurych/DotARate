package com.ez.dotarate.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ez.dotarate.R
import com.ez.dotarate.adapters.ViewPagerAdapter
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.database.User
import com.ez.dotarate.databinding.FragmentProfileSearchBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.view.activities.MainActivity
import com.ez.dotarate.viewModel.ProfileSearchViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfileSearchFragment : BaseFragment<ProfileSearchViewModel, FragmentProfileSearchBinding>() {
    private var mOldVerticalOffset = 0

    override fun layout() = R.layout.fragment_profile_search

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        vb.vm = vm

        val id32 = arguments!!.getInt(USER_ID_KEY)

        val user = User()
        user.id = id32

        if (savedInstanceState == null) {
            vm.fetchUser(id32)
            vm.fetchWinsAndLosses(id32)
        }

        vm.liveUser.observe(this, Observer {
            activity!!.title = it.profile.personaname
            (activity!! as MainActivity).searchUserName = it.profile.personaname
            user.name = it.profile.personaname
            user.avatarUrl = it.profile.avatarfull
            user.rankId = it.rank_tier

            if (user.losses != null) vb.user = user
        })

        vm.liveWinsAndLosses.observe(this, Observer {
            user.wins = it.win
            user.losses = it.lose

            if (user.rankId != null) vb.user = user
        })

        val listOfFragments: ArrayList<Fragment> = arrayListOf(GamesSearchFragment(id32), MphSearchFragment(id32))

        vb.vpContainerProfileSearch.adapter =
            ViewPagerAdapter(listOfFragments, this, vm.isNeedPositionToStartGames, vm.isNeedPositionToStartMph)

        TabLayoutMediator(vb.tabs, vb.vpContainerProfileSearch,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        tab.icon = context!!.getDrawable(R.drawable.vp_games_icon_selector)
                    }
                    1 -> {
                        tab.icon = context!!.getDrawable(R.drawable.vp_mph_icon_selector)
                    }
                }
            }).attach()

        val appBar = vb.ablProfileSearch
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset == -491) {
                vm.isNeedPositionToStartGames.set(false)
                vm.isNeedPositionToStartMph.set(false)
            }

            mOldVerticalOffset = if (verticalOffset > mOldVerticalOffset) {
                if (vb.vpContainerProfileSearch.currentItem == 0) vm.isNeedPositionToStartMph.set(true)
                else vm.isNeedPositionToStartGames.set(true)
                verticalOffset
            } else {
                verticalOffset
            }
        })
    }
}