package com.ez.dotarate.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveGames(listGames: ArrayList<Games>)

    @Query("SELECT * FROM games")
    fun getGames(): DataSource.Factory<Int, Games>
}