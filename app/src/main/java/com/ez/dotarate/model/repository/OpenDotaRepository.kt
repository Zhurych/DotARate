package com.ez.dotarate.model.repository

import androidx.paging.DataSource
import com.ez.dotarate.database.Game
import com.ez.dotarate.database.GameDao
import com.ez.dotarate.model.GameDetail
import retrofit2.Response

interface OpenDotaRepository {
    // Database
    suspend fun saveGames(listGames: ArrayList<Game>): List<Long>
    fun getGames(): DataSource.Factory<Int, Game>

    // Network
    suspend fun getMatches(id32: Int): Response<ArrayList<Game>>
    suspend fun getGameDetail(id: Long): Response<GameDetail>
}