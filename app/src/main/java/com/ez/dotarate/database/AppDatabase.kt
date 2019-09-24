package com.ez.dotarate.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Аннотацией Database помечаем основной класс по работе с базой данных
 * Этот класс должен быть абстрактным и наследовать RoomDatabase
 * В Database классе необходимо описать абстрактные методы для получения Dao объектов
 */
@Database(entities = [UserId::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userIdDao(): UserIdDao
}