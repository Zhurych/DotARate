package com.ez.dotarate.model.repository

import androidx.paging.DataSource
import com.ez.dotarate.database.Game
import com.ez.dotarate.database.Hero
import com.ez.dotarate.model.GameDetail
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response

interface OpenDotaRepository {
    // Database
    suspend fun saveGames(listGames: ArrayList<Game>): List<Long>
    fun getGamesDataSourceFactory(isLocal: Boolean, scope: CoroutineScope, id32: Int): DataSource.Factory<Int, Game>
    suspend fun saveHeroes(listHeroes: ArrayList<Hero>): List<Long>
    fun getHeroesDataSourceFactory(
        isLocal: Boolean,
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Hero>

    // Network
    suspend fun getMatches(id32: Int): Response<ArrayList<Game>>
    suspend fun getGameDetail(id: Long): Response<GameDetail>
    suspend fun fetchHeroes(id32: Int): Response<ArrayList<Hero>>
    suspend fun fetchMatches(id32: Int, loadPosition: Int, limitSize:Int): Response<ArrayList<Game>>
}