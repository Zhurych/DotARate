package com.ez.dotarate.model.repository

import com.ez.dotarate.database.UserId
import com.ez.dotarate.database.UserIdDao
import com.ez.dotarate.model.User
import retrofit2.Response

interface UserRepository {
    // Database
    suspend fun saveUserId(userId: UserId)
    suspend fun getUserId(): UserId?
    suspend fun logout()

    // Network
    suspend fun getUser(id: Long): Response<User>
}