package com.ez.dotarate.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentLoginBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.LoginViewModel


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override fun layout() = R.layout.fragment_login

    override fun afterCreateView(view: View) {
        vb.loginListener = loginListener
    }

    val loginListener: View.OnClickListener = View.OnClickListener {
        // Можно получить NavController
        it.findNavController().navigate(R.id.steamFragment)

        //val intent = Intent(activity, MainActivity::class.java)
        // start your next activity
        //startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
}
