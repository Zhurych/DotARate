package com.ez.dotarate.database

import androidx.room.*


@Dao
interface UserIdDao {

    @Query("SELECT * FROM userid")
    fun getId(): UserId

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveId(id: UserId)
}