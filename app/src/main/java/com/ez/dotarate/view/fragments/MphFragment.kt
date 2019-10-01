package com.ez.dotarate.view.fragments

import android.view.View
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentMphBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.MphViewModel

class MphFragment : BaseFragment<MphViewModel, FragmentMphBinding>() {
    override fun layout() = R.layout.fragment_mph

    override fun afterCreateView(view: View) {

    }
}