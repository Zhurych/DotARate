package com.ez.dotarate.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `heroes` (`hero_id` INTEGER PRIMARY KEY NOT NULL, `games` INTEGER NOT NULL, `last_played` INTEGER NOT NULL, `win` INTEGER NOT NULL)")
    }
}