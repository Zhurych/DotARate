package com.ez.dotarate.view.activities

import android.os.Bundle
import com.ez.dotarate.R
import com.ez.dotarate.databinding.ActivityMainBinding
import com.ez.dotarate.view.BaseActivity
import com.ez.dotarate.viewModel.MainViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun layout() = R.layout.activity_main

    override fun afterCreate(savedInstanceState: Bundle?) {

    }
}
