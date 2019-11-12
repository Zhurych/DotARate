package com.ez.dotarate.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentMphBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.MphViewModel

class MphFragment : BaseFragment<MphViewModel, FragmentMphBinding>() {
    override fun layout() = R.layout.fragment_mph

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        activity?.setTitle(R.string.mph_screen_title)
        Log.d("MyLogs", "MphFragment. AfterCreateView")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "MphFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "MphFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "MphFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "MphFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "MphFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "MphFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "MphFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "MphFragment. onDestroy")
    }
}