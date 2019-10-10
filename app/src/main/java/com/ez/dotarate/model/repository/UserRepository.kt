package com.ez.dotarate.model.repository

import com.ez.dotarate.database.Games
import com.ez.dotarate.database.GamesDao
import com.ez.dotarate.database.UserId
import com.ez.dotarate.database.UserIdDao
import com.ez.dotarate.model.User

interface UserRepository {
    // Database
    suspend fun saveUserId(dao: UserIdDao, userId: UserId)
    suspend fun getUserId(dao: UserIdDao): UserId?
    suspend fun logout(dao: UserIdDao)
    suspend fun saveGames(dao:GamesDao, listGames: ArrayList<Games>)

    // Network
    suspend fun getUser(id: Long): User
    suspend fun getMatches(id32: Int): ArrayList<Games>
}