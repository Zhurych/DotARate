package com.ez.dotarate.view.fragments

import android.view.View
import androidx.lifecycle.Observer
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentProfileBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.ProfileViewModel


class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {


    override fun layout() = R.layout.fragment_profile

    override fun afterCreateView(view: View) {
        val id = activity!!.intent.getLongExtra("id", 0)

        vm.getUser(id)
        vm.data.observe(this, Observer {
            vb.user = it
        })
    }
}