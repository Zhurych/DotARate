package com.ez.dotarate.view.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.navigation.findNavController
import com.ez.dotarate.R
import com.ez.dotarate.databinding.ActivityMainBinding
import com.ez.dotarate.view.BaseActivity
import com.ez.dotarate.viewModel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private val controller by lazy { findNavController(R.id.main_nav_host_fragment) }

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
                        controller.navigate(R.id.gamesFragment)
                        return true
                    }
                    R.id.nav_mph -> {
                        title = getString(R.string.mph_screen_title)
                        currentId = R.id.nav_mph
                        controller.navigate(R.id.mphFragment)
                        return true
                    }
                    R.id.nav_profile -> {
                        title = getString(R.string.profile_screen_title)
                        currentId = R.id.nav_profile
                        controller.navigate(R.id.profileFragment)
                        return true
                    }
                }

                return false
            }
        }

    override fun layout() = R.layout.activity_main

    override fun afterCreate(savedInstanceState: Bundle?) {
        vb.bottomNavigation.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)

        setSupportActionBar(vb.fpToolbar)
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
