package com.ez.dotarate.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserIdDao {

    @Query("SELECT * FROM userid")
    fun getId(): UserId

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveId(id: UserId)

    @Query("DELETE FROM userid")
    fun deleteUser()
}