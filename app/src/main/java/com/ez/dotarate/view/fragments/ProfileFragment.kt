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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.ez.dotarate.R
import com.ez.dotarate.adapters.DemoViewPagerAdapter
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.databinding.FragmentProfileBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.view.activities.StartActivity
import com.ez.dotarate.viewModel.ProfileViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    override fun layout() = R.layout.fragment_profile

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        activity?.setTitle(R.string.profile_screen_title)
        Log.d("MyLogs", "ProfileFragment. CreateView")

        val id = activity!!.intent.getLongExtra(USER_ID_KEY, 0)

        // Говорим фрагменту, что ему нужно отобразить меню
        setHasOptionsMenu(true)

        if (savedInstanceState == null) {
            vm.getUser(id)
        }

        vm.data.observe(this, Observer {
            vb.user = it
        })

        vb.viewpager.adapter = DemoViewPagerAdapter()
//        TabLayoutMediator(tabs, viewpager, object : TabLayoutMediator.OnConfigureTabCallback {
//            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
//                // Styling each tab here
//                tab.setText("Tab $position")
//            }
//        }).attach()


        Log.d("MyLogs", "")
        TabLayoutMediator(vb.tabs, vb.viewpager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> { tab.text = "1"}
                    1 -> { tab.text = "TAB TWO"}
                    2 -> { tab.text = "TAB 3"}
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