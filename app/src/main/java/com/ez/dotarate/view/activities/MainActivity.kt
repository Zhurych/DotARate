package com.ez.dotarate.view.activities

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import com.ez.dotarate.R
import com.ez.dotarate.databinding.ActivityMainBinding
import com.ez.dotarate.extensions.popMyBackStack
import com.ez.dotarate.extensions.setupWithNavController
import com.ez.dotarate.view.BaseActivity
import com.ez.dotarate.viewModel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * "Вкладка" - это NavHostFragment
 */

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private val fm = supportFragmentManager
    private lateinit var mBottomNavigationView: BottomNavigationView

    override fun layout() = R.layout.activity_main

    override fun afterCreate(savedInstanceState: Bundle?) {

        mBottomNavigationView = vb.bottomNavigation

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState

        setSupportActionBar(vb.fpToolbar)
    }

    override fun onBackPressed() {

        val currentNavController = vm.currentNavController.value as NavController

        // Если текущий фрагмент это не стартовый фрагмент в этой вкладке, то
        if (currentNavController.currentDestination!!.id != currentNavController.graph.startDestination) {
            // Если в стеке текущей "вкладки" нет фрагментов - переходим на предыдущую вкладку, если она есть в стеке вкладок
            // Иначе просто переходим вниз по стеку
            currentNavController.popBackStack()
        } else {
            // Если в стеке больше одной "вкладки", то переходим на предыдущую
            // Иначе закрываем текущую вкладку. Т.е. приложение закрывается
            if (vm.mBackStack.size > 1) {
                vm.currentNavController.value = popMyBackStack(fm, vm.mBackStack, mBottomNavigationView)
            } else {
                super.onBackPressed()
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {

        val navGraphIds = listOf(R.navigation.games, R.navigation.mph, R.navigation.profile)

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = mBottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.main_container,
            intent = intent,
            backStack = vm.mBackStack
        )

        vm.currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return vm.currentNavController.value?.navigateUp() ?: false
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