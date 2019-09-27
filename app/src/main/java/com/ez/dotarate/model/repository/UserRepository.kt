package com.ez.dotarate.model.repository

import com.ez.dotarate.database.UserId
import com.ez.dotarate.database.UserIdDao

interface UserRepository {
    fun saveUserId(dao: UserIdDao, userId: UserId)
    fun getUserId(dao: UserIdDao): UserId?
}