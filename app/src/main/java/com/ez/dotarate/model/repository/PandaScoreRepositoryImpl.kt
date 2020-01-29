package com.ez.dotarate.model.repository

import com.ez.dotarate.constants.PANDASCORE_TOKEN
import com.ez.dotarate.model.UpcomingGame
import com.ez.dotarate.network.ServerApi
import javax.inject.Inject
import javax.inject.Named

class PandaScoreRepositoryImpl
@Inject constructor(
    @Named("PandaScoreApi") private val api: ServerApi
) : PandaScoreRepository {

    override fun fetchUpcomingMatches(page: Int, loadSize: Int): ArrayList<UpcomingGame> {
        val call = api.fetchUpcomingGames(
            token = PANDASCORE_TOKEN,
            page = page,
            loadSize = loadSize
        )

        val response = call.execute()

        return if (response.isSuccessful) {
            response.body() ?: ArrayList()
        } else ArrayList()
    }
}