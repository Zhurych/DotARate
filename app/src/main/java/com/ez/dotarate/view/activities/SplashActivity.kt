package com.ez.dotarate.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ez.dotarate.viewModel.SplashViewModel

const val USER_ID_KEY = "id"

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm = ViewModelProviders.of(this).get(SplashViewModel::class.java)

        vm.getUser()
        vm.data.observe(this, Observer {
            if (it == null) {
                val intent = Intent(this, StartActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MainActivity::class.java)

                intent.putExtra(USER_ID_KEY, it.id)
                startActivity(intent)
                finish()
            }
        })
    }
}
