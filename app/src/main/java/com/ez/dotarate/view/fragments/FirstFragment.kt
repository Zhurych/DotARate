package com.ez.dotarate.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentFirstBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.FirstFragViewModel

class FirstFragment : BaseFragment<FirstFragViewModel, FragmentFirstBinding>() {

    override fun layout() = R.layout.fragment_first

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        //activity?.setTitle(R.string.mph_screen_title)
        Log.d("MyLogs", "FirstFragment. AfterCreateView")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "FirstFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "FirstFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "FirstFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "FirstFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "FirstFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "FirstFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "FirstFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "FirstFragment. onDestroy")
    }
}