package com.ez.dotarate.database

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(list: ArrayList<Hero>)

    @Query("SELECT * FROM heroes ORDER BY games DESC")
    fun getHeroes(): DataSource.Factory<Int, Hero>

    @Query("DELETE FROM heroes")
    suspend fun deleteHeroes()
}