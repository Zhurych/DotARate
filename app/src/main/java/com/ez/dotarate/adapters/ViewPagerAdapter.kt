package com.ez.dotarate.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ez.dotarate.view.fragments.GamesFragment
import com.ez.dotarate.view.fragments.MphFragment


class ViewPagerAdapter(fragmentActivity: Fragment, private val listFragments: ArrayList<Fragment>) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int) = listFragments[position]

    override fun getItemCount(): Int {
        return listFragments.size
    }
}