package com.ez.dotarate.view.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.ez.dotarate.R
import com.ez.dotarate.databinding.ActivityMainBinding
import com.ez.dotarate.view.BaseActivity
import com.ez.dotarate.view.fragments.GamesFragment
import com.ez.dotarate.view.fragments.MphFragment
import com.ez.dotarate.view.fragments.ProfileFragment
import com.ez.dotarate.viewModel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private val fragmentGames = GamesFragment()
    private val fragmentMph = MphFragment()
    private val fragmentProfile = ProfileFragment()
    private val fm = supportFragmentManager

    private var active: Fragment = fragmentGames

    private val mNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {

            private var currentId = R.id.nav_games

            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                if (currentId == menuItem.itemId) {
                    return true
                }

                when (menuItem.itemId) {
                    R.id.nav_games -> {
                        currentId = R.id.nav_games
                        fm.beginTransaction().hide(active).show(fragmentGames).commit()
                        active = fragmentGames
                        return true
                    }
                    R.id.nav_mph -> {
                        currentId = R.id.nav_mph
                        fm.beginTransaction().hide(active).show(fragmentMph).commit()
                        active = fragmentMph
                        return true
                    }
                    R.id.nav_profile -> {
                        title = ""
                        currentId = R.id.nav_profile
                        fm.beginTransaction().hide(active).show(fragmentProfile).commit()
                        active = fragmentProfile
                        return true
                    }
                }

                return false
            }
        }

    override fun layout() = R.layout.activity_main

    override fun afterCreate(savedInstanceState: Bundle?) {
        vb.bottomNavigation.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)

        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(vb.fpToolbar)

        if (savedInstanceState == null) {
            fm.beginTransaction().add(R.id.fragment_container, fragmentProfile, "3")
                .hide(fragmentProfile).commit()
            fm.beginTransaction().add(R.id.fragment_container, fragmentMph, "2").hide(fragmentMph)
                .commit()
            fm.beginTransaction().add(R.id.fragment_container, fragmentGames, "1").commit()
        }
    }
}
