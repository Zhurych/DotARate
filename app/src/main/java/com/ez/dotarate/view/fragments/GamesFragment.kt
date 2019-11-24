package com.ez.dotarate.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ez.dotarate.R
import com.ez.dotarate.adapters.GamesAdapter
import com.ez.dotarate.constants.CONVERTER_NUMBER
import com.ez.dotarate.constants.MATCH_ID_KEY
import com.ez.dotarate.constants.USER_ID_KEY
import com.ez.dotarate.database.Game
import com.ez.dotarate.databinding.FragmentGamesBinding
import com.ez.dotarate.listeners.ClickListener
import com.ez.dotarate.listeners.RecyclerTouchListener
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.GamesViewModel


class GamesFragment : BaseFragment<GamesViewModel, FragmentGamesBinding>() {
    private val adapter = GamesAdapter()

    private lateinit var pagedList: PagedList<Game>
    //private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun layout() = R.layout.fragment_games

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {

        Log.d("MyLogs", "GamesFragment. AfterCreateView")

        vb.adapter = adapter

        val id32: Int =
            (activity!!.intent!!.getLongExtra(USER_ID_KEY, 0) - CONVERTER_NUMBER).toInt()

//        swipeRefreshLayout = vb.srlGamesFragment
//        swipeRefreshLayout.setOnRefreshListener {
//            vm.getGames(id32)
//            Log.d("MyLogs", "ОБНОВЛЕНИЕ СТРАНИЦЫ")
//        }

        // Need to set LayoutManager
        val recyclerView = vb.rvGamesFragment
        recyclerView.layoutManager = LinearLayoutManager(activity)
        // Set touch listener
        recyclerView.addOnItemTouchListener(
            RecyclerTouchListener(
                context!!,
                recyclerView,
                object : ClickListener {
                    override fun onClick(view: View, position: Int) {
                        try {
                            val game = pagedList[position]

                            val bundle = Bundle()
                            bundle.putLong(MATCH_ID_KEY, game!!.match_id)
                            if (findNavController().currentDestination?.id == R.id.gamesFragment) {
                                Log.d("MyLogs", "games Fragment. КОНТРОЛЕР = ${findNavController()}")
                                Log.d("MyLogs", "games Fragment. id ТЕКУЩЕГО ФРАГМЕНТА = ${findNavController().currentDestination?.id}")
                                findNavController().navigate(
                                    R.id.action_gamesFragment_to_gameDetailFragment,
                                    bundle
                                )
                            } else {
                                Log.d("MyLogs", "games Fragment. КОНТРОЛЕР = ${findNavController()}")
                                Log.d("MyLogs", "games Fragment. id ТЕКУЩЕГО ФРАГМЕНТА = ${findNavController().currentDestination?.id}")
                            }
                        } catch (e: NullPointerException) {
                            Toast.makeText(activity, "Пустые данные", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onLongClick(view: View, position: Int) {

                    }
                })
        )

        if (savedInstanceState == null) vm.getGames(id32)

        // LiveData<PagedList<Game>> subscriber
        vm.liveGame.observe(this, Observer {
            Log.d("MyLogs", "GamesFragment.  LiveData с PagedList")
            if (it != null && it.size > 0) {
                Log.d("MyLogs", "GamesFragment. PagedList = $it")
                vm.isGamesEmpty.set(true)
                pagedList = it
            } else {

                Log.d("MyLogs", "GamesFragment. PagedList = $it")
            }
            // Need to use submitList to set the PagedListAdapter value
            adapter.submitList(it)
        })

        vm.errorLiveData.observe(this, Observer {
            it.getContentIfNotHandled()?.let { its ->
                // Only proceed if the event has never been handled
                Toast.makeText(activity, its, Toast.LENGTH_SHORT).show()
            }
        })

        vm.isLoaded.observe(this, Observer {
           // if (it) swipeRefreshLayout.isRefreshing = false
        })

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