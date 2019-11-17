package com.ez.dotarate.model.repository

import android.util.Log
import androidx.paging.DataSource
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.database.Game
import com.ez.dotarate.model.GameDetail
import com.ez.dotarate.network.ServerApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class OpenDotaRepositoryImpl @Inject
constructor(@Named("OpenDota") private val api: ServerApi, private val db: AppDatabase) : OpenDotaRepository {

    override fun getGames(): DataSource.Factory<Int, Game> = db.gameDao().getGames()

    override suspend fun saveGames(listGames: ArrayList<Game>): List<Long> {
        return db.gameDao().saveGames(listGames)
    }

    /**
     * GET request.
     * Receive Game Response that contains 100 matches by default
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a Response<ArrayList<Game>>.
     */
    override suspend fun getMatches(id32: Int): Response<ArrayList<Game>> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС")
        return api.getGames(id32)
    }

        /**
         * GET request.
         * Receive detail Game
         * We don’t need to call enqueue() and implement callbacks anymore!
         * But notice, now our repo method is suspend too and returns a Response<GameDetail>.
         */
        override suspend fun getGameDetail(id: Long): Response<GameDetail> {
            Log.d("MyLogs", "ПОШЁЛ ЗАПРОС")
            return api.getGameDetail(id)
        }
}