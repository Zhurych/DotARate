package com.ez.dotarate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Аннотацией Database помечаем основной класс по работе с базой данных
 * Этот класс должен быть абстрактным и наследовать RoomDatabase
 * В Database классе необходимо описать абстрактные методы для получения Dao объектов
 */
@Database(entities = [UserId::class, Games::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userIdDao(): UserIdDao
   abstract fun gamesDao(): GamesDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "database")
            .build()
    }
}