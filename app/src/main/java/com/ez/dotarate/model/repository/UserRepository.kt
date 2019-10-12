package com.ez.dotarate.model.repository

import com.ez.dotarate.database.*
import com.ez.dotarate.model.User
import retrofit2.Response

interface UserRepository {
    // Database
    suspend fun saveUserId(dao: UserIdDao, userId: UserId)
    suspend fun getUserId(dao: UserIdDao): UserId?
    suspend fun logout(dao: UserIdDao)
    suspend fun saveGames(dao:GameDao, listGames: ArrayList<Game>): List<Long>

    // Network
    suspend fun getUser(id: Long): Response<User>
    suspend fun getMatches(id32: Int): Response<ArrayList<Game>>
}