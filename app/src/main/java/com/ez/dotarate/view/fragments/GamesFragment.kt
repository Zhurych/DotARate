package com.ez.dotarate.view.fragments

import android.view.View
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentGamesBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.GamesViewModel


class GamesFragment : BaseFragment<GamesViewModel, FragmentGamesBinding>() {
    override fun layout() = R.layout.fragment_games

    override fun afterCreateView(view: View) {

    }

}
