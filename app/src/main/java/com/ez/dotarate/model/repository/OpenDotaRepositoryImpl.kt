package com.ez.dotarate.model.repository

import com.ez.dotarate.database.Game
import com.ez.dotarate.database.GameDao
import com.ez.dotarate.network.ServerApi
import javax.inject.Inject

class OpenDotaRepositoryImpl @Inject
constructor(private val api: ServerApi) : OpenDotaRepository {

    override suspend fun saveGames(dao: GameDao, listGames: ArrayList<Game>): List<Long> {
        return dao.saveGames(listGames)
    }

    /**
     * GET request.
     * Receive Game Response that contains 100 matches by default
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a Response<ArrayList<Game>>.
     */
    override suspend fun getMatches(id32: Int) = api.getGames(id32)
}