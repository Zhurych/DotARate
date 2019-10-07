package com.ez.dotarate.model.repository

import com.ez.dotarate.database.UserId
import com.ez.dotarate.database.UserIdDao
import com.ez.dotarate.model.User

interface UserRepository {
    // Database
    suspend fun saveUserId(dao: UserIdDao, userId: UserId)
    suspend fun getUserId(dao: UserIdDao): UserId?
    suspend fun logout(dao: UserIdDao)

    // Network
    suspend fun getUser(id: Long): User
}