package com.ez.dotarate.view.fragments

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentSearchUsersBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.view.activities.MainActivity
import com.ez.dotarate.viewModel.SearchUsersViewModel


class SearchUsersFragment : BaseFragment<SearchUsersViewModel, FragmentSearchUsersBinding>() {
    override fun layout() = R.layout.fragment_search_users

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        Log.d("MyLogs", "SearchUsersFragment. afterCreateView")
        //(activity as MainActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "SearchUsersFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "SearchUsersFragment. onResume")
        // Show keyboard
        val imm = activity!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(vb.searchEditText, InputMethodManager.SHOW_IMPLICIT)
        // Enable back button
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "SearchUsersFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "SearchUsersFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "SearchUsersFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "SearchUsersFragment. onStop")
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "SearchUsersFragment. onDestroy")
    }
}