package com.ez.dotarate.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import java.util.*
import javax.inject.Inject


class MainViewModel @Inject
constructor(
    val mBackStack: Stack<NavHostFragment>,
    var currentNavController: MutableLiveData<NavController>
) : ViewModel() {

}