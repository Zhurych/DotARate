package com.ez.dotarate.di

import android.app.Application
import androidx.room.Room
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.database.MIGRATION_1_2
import com.ez.dotarate.database.MIGRATION_2_3
import com.ez.dotarate.database.MIGRATION_3_4
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, AppDatabase::class.java, "database")
            .build()
}