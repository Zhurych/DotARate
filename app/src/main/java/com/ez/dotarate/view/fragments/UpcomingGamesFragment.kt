package com.ez.dotarate.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ez.dotarate.R
import com.ez.dotarate.adapters.UpcomingGamesAdapter
import com.ez.dotarate.databinding.FragmentUpcomingGamesBinding
import com.ez.dotarate.model.UpcomingGame
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.UpcomingGamesViewModel
import javax.inject.Inject


class UpcomingGamesFragment : BaseFragment<UpcomingGamesViewModel, FragmentUpcomingGamesBinding>() {

    @Inject
    lateinit var adapter: UpcomingGamesAdapter

    private lateinit var pagedListUpcomingGames: PagedList<UpcomingGame>

    override fun layout() = R.layout.fragment_upcoming_games

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {
        vb.adapter = adapter
        vb.isDataLoaded = vm.isDataLoaded

        val recyclerView = vb.rvUpcomingGames
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        vm.liveUpcomingGames.observe(this, Observer {
            Log.d("MyLogs", "UpcomingGamesFragment. Подписчик livedata. Значение = $it")
            pagedListUpcomingGames = it
            adapter.submitList(it)

            if (it.isNotEmpty()) vm.isDataLoaded.set(true)
        })

        Log.d("MyLogs", "UpcomingGamesFragment. AfterCreateView")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "UpcomingGamesFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "UpcomingGamesFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "UpcomingGamesFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "UpcomingGamesFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "UpcomingGamesFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "UpcomingGamesFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "UpcomingGamesFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "UpcomingGamesFragment. onDestroy")
    }
}