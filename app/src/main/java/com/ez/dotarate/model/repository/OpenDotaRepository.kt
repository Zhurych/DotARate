package com.ez.dotarate.model.repository

import com.ez.dotarate.database.Game
import com.ez.dotarate.database.GameDao
import retrofit2.Response

interface OpenDotaRepository {
    // Database
    suspend fun saveGames(dao: GameDao, listGames: ArrayList<Game>): List<Long>

    // Network
    suspend fun getMatches(id32: Int): Response<ArrayList<Game>>
}