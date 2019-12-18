package com.ez.dotarate.view.activities

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ez.dotarate.IOnTouchEvent
import com.ez.dotarate.R
import com.ez.dotarate.constants.GAME_DETAIL_FRAGMENT_LABEL
import com.ez.dotarate.constants.PROFILE_FRAGMENT_LABEL
import com.ez.dotarate.constants.PROFILE_SEARCH_FRAGMENT_LABEL
import com.ez.dotarate.databinding.ActivityMainBinding
import com.ez.dotarate.extensions.graphIdToTagMap
import com.ez.dotarate.extensions.popMyBackStack
import com.ez.dotarate.extensions.setupWithNavController
import com.ez.dotarate.view.BaseActivity
import com.ez.dotarate.viewModel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * "Вкладка" - это NavHostFragment
 */

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    lateinit var mActionBar: ActionBar
    private val fm = supportFragmentManager
    private lateinit var mBottomNavigationView: BottomNavigationView
    private val navControllerObserver by lazy {
        Observer<NavController> {

            when (it.graph.id) {
                graphIdToTagMap.keyAt(0) -> {
                    title = getString(R.string.games_screen_title)
                }
                graphIdToTagMap.keyAt(1) -> {
                    title = ""
                    // TODO: сделать кнопку назад
//                    actionBar?.setDisplayHomeAsUpEnabled(true)
//                    actionBar?.setDisplayShowHomeEnabled(true)
//                actionBar.setIcon(R.drawable.ic_search_title)
                }
                graphIdToTagMap.keyAt(2) -> {
                    title = when (it.currentDestination!!.label) {
                        PROFILE_FRAGMENT_LABEL -> userName
                        GAME_DETAIL_FRAGMENT_LABEL -> ""
                        PROFILE_SEARCH_FRAGMENT_LABEL -> searchUserName
                        else -> userName
                    }
                    //actionBar!!.setDisplayShowHomeEnabled(false)
                }
            }
        }
    }

    lateinit var userName: String
    lateinit var searchUserName: String

    override fun layout() = R.layout.activity_main

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val navHostFragmentProfile =
            fm.findFragmentByTag(graphIdToTagMap.valueAt(2)) as NavHostFragment

        val fragment =
            navHostFragmentProfile.childFragmentManager.findFragmentById(R.id.main_container)

        (fragment as? IOnTouchEvent)?.onTouchEvent(event)

        return true
    }

    override fun afterCreate(savedInstanceState: Bundle?) {

        mBottomNavigationView = vb.bottomNavigation

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState

        setSupportActionBar(vb.fpToolbar)
        mActionBar = supportActionBar!!

        vm.userNameLive.observe(this, Observer { userName = it?.name ?: "" })
        vm.searchUserNameLive.observe(this, Observer { searchUserName = it })

        vm.currentNavController.observe(this, navControllerObserver)
    }

    override fun onBackPressed() {

        val currentNavController = vm.currentNavController.value as NavController

        // Если текущий фрагмент это не стартовый фрагмент в этой вкладке, то
        if (currentNavController.currentDestination!!.id != currentNavController.graph.startDestination) {
            // Если в стеке текущей "вкладки" есть несколько фрагментов - просто переходим вниз по стеку
            // Иначе переходим на предыдущую вкладку, если она есть в стеке вкладок
            currentNavController.popBackStack()
            // Notify the LiveData<NavController> of a fragment change in order to change the title name
            vm.currentNavController.value = currentNavController
        } else {
            // Если в стеке больше одной "вкладки", то переходим на предыдущую
            // Иначе закрываем текущую вкладку. Т.е. приложение закрывается
            if (vm.mBackStack.size > 1) {
                vm.currentNavController.value =
                    popMyBackStack(fm, vm.mBackStack, mBottomNavigationView)
            } else {
                vm.mBackStack.pop()
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

        val navGraphIds = listOf(R.navigation.first, R.navigation.search, R.navigation.profile)

        // Setup the bottom navigation view with a list of navigation graphs
        mBottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = fm,
            containerId = R.id.main_container,
            intent = intent,
            backStack = vm.mBackStack,
            liveNavController = vm.currentNavController
        )
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MyLogs", "MainActivity. onSaveInstanceState")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "MainActivity. onDestroy")
    }
}