package com.ez.dotarate

import com.ez.dotarate.database.AppDatabase
import androidx.room.Room
import android.app.Application


class App : Application() {

    var database: AppDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }
}