package com.ez.dotarate.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.ez.dotarate.R
import com.ez.dotarate.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "MainActivity. OnCreate")
        val vb: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogs", "MainActivity. onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogs", "MainActivity. onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogs", "MainActivity. onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogs", "MainActivity. onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogs", "MainActivity. onDestroy")
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()
}
