package com.ez.dotarate.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentGamesBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.GamesViewModel


class GamesFragment : BaseFragment<GamesViewModel, FragmentGamesBinding>() {
    override fun layout() = R.layout.fragment_games

    override fun afterCreateView(view: View) {
        activity?.setTitle(R.string.games_screen_title)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "GamesFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "GamesFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "GamesFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "GamesFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "GamesFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "GamesFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "GamesFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "GamesFragment. onDestroy")
    }
}