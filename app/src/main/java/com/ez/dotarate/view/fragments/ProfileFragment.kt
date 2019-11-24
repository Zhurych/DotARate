package com.ez.dotarate.view.fragments

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.ez.dotarate.R
import com.ez.dotarate.adapters.ViewPagerAdapter
import com.ez.dotarate.constants.CONVERTER_NUMBER
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.databinding.FragmentProfileBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.view.activities.StartActivity
import com.ez.dotarate.viewModel.ProfileViewModel
import com.google.android.material.tabs.TabLayoutMediator


class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override fun layout() = R.layout.fragment_profile

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        Log.d("MyLogs", "ProfileFragment. CreateView")

        val id = activity!!.intent.getLongExtra(USER_ID_KEY, 0) - CONVERTER_NUMBER

        // Говорим фрагменту, что ему нужно отобразить меню
        setHasOptionsMenu(true)

        if (savedInstanceState == null) {
            vm.getUser(id)
            vm.getWinsAndLosses(id)
        }

        vm.userLiveData.observe(this, Observer {
            vb.user = it
            //activity?.title = it.profile.personaname
        })

        vm.wlLiveData.observe(this, Observer {
            vb.winsAndLosses = it
        })

        vb.vpContainer.adapter = ViewPagerAdapter(this, arrayListOf(GamesFragment(), MphFragment()))

        TabLayoutMediator(vb.tabs, vb.vpContainer,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> { tab.icon = context!!.getDrawable(R.drawable.vp_games_icon_selector)}
                    1 -> { tab.icon = context!!.getDrawable(R.drawable.vp_mph_icon_selector)}
                }
            }).attach()

//        vm.errorLiveData.observe(this, Observer {
//            Log.d("MyLogs", "ProfileFragment. live Data с ошибкой")
//            it.getContentIfNotHandled()?.let { its ->
//                // Only proceed if the event has never been handled
//                Toast.makeText(activity, its, Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
    }

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.proileLogout -> {
                context
                if (context != null) {
                    val builder = AlertDialog.Builder(context!!)

                    builder.setMessage(R.string.profile_screen_dialog_logout)
                    builder.setPositiveButton(R.string.yes) { _: DialogInterface, id: Int ->
                        vm.logout()
                        val intent = Intent(activity, StartActivity::class.java)
                        startActivity(intent)
                        activity!!.finish()
                    }

                    builder.setNegativeButton(R.string.no) { dialog: DialogInterface, id: Int -> dialog.dismiss() }

                    val alert = builder.create()
                    alert.show()
                }
            }
        }

        return true
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "ProfileFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "ProfileFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "ProfileFragment. onCreate.    ---    bundle = $savedInstanceState")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "ProfileFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "ProfileFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "ProfileFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "ProfileFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "ProfileFragment. onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MyLogs", "ProfileFragment. onSaveInstanceState")
    }
}