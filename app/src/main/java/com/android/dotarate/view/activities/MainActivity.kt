package com.android.dotarate.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.dotarate.R

class MainActivity : AppCompatActivity() {

    // Navigation Component.
    // NavController. Отвечает за предоставление графа(NavGraph) в контейнер(NavHostFragment). Контейнер предоставляет контролер
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Получаем контролер из контейнера по ID
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }
}
