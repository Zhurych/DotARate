package com.ez.dotarate.view.activities

import android.os.Bundle
import android.util.Log
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
                        title = getString(R.string.games_screen_title)
                        currentId = R.id.nav_games
                        Log.d("MyLogs", "MainActivity. fragmentGames = $fragmentGames")
                        fm.beginTransaction().hide(active).show(fragmentGames).commit()
                        active = fragmentGames
                        return true
                    }
                    R.id.nav_mph -> {
                        title = getString(R.string.mph_screen_title)
                        currentId = R.id.nav_mph
                        Log.d("MyLogs", "MainActivity. fragmentMph = $fragmentMph")
                        fm.beginTransaction().hide(active).show(fragmentMph).commit()
                        active = fragmentMph
                        return true
                    }
                    R.id.nav_profile -> {
                        title = getString(R.string.profile_screen_title)
                        currentId = R.id.nav_profile
                        Log.d("MyLogs", "MainActivity. fragmentProfile = $fragmentProfile")
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
            Log.d("MyLogs", "MainActivity. savedInstanceState = $savedInstanceState")
            Log.d("MyLogs", "MainActivity. fm = $fm")
            fm.beginTransaction().add(R.id.fragment_container, fragmentProfile, "3")
                .hide(fragmentProfile).commit()
            fm.beginTransaction().add(R.id.fragment_container, fragmentMph, "2").hide(fragmentMph)
                .commit()
            fm.beginTransaction().add(R.id.fragment_container, fragmentGames, "1").commit()
        } else {
            Log.d("MyLogs", "MainActivity. savedInstanceState = $savedInstanceState")
            Log.d("MyLogs", "MainActivity. fm = $fm")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "MainActivity. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "MainActivity. onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MyLogs", "MainActivity. onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "MainActivity. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "MainActivity. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "MainActivity. onDestroy")
    }
}
