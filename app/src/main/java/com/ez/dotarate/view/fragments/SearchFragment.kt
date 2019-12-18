package com.ez.dotarate.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentSearchBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.viewModel.SearchViewModel

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    override fun layout() = R.layout.fragment_search

    override fun afterCreateView(view: View, savedInstanceState: Bundle?) {

        // Говорим фрагменту, что ему нужно отобразить меню
        //setHasOptionsMenu(true)

//        vb.searchEditText.setOnFocusChangeListener { v, hasFocus ->
//            if (hasFocus) {
//                Log.d("MyLogs", "ПОЛУЧЕН ФОКУС В SEARCH")
//                //vb.ivSearch.visibility = View.INVISIBLE
//                findNavController().navigate(R.id.searchUsersFragment)
//            } else {
//                //vb.ivSearch.visibility = View.VISIBLE
//            }
//        }

        vb.tvSearch.setOnClickListener {
            findNavController().navigate(R.id.searchUsersFragment)
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.search_menu, menu)
//
//        (menu.findItem(R.id.search).actionView as SearchView).apply {
//            setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
//            //isIconified = false
//            //setPadding(0, 0, 0, 0)
//
//            //maxWidth = Int.MAX_VALUE
//            queryHint = getString(R.string.search_screen_title)
//        }
//    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "SearchFragment. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "SearchFragment. onResume")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "SearchFragment. onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "SearchFragment. onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLogs", "SearchFragment. onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "SearchFragment. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "SearchFragment. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "SearchFragment. onDestroy")
    }
}