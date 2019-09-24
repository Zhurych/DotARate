package com.ez.dotarate.database

import androidx.room.Dao
import androidx.room.Query


@Dao
interface UserIdDao {

    @Query("SELECT * FROM userid")
    fun getId(): UserId

}