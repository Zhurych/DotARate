package com.ez.dotarate.model.repository

import android.util.Log
import androidx.paging.DataSource
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.database.Game
import com.ez.dotarate.database.Hero
import com.ez.dotarate.model.GameDetail
import com.ez.dotarate.network.ServerApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class OpenDotaRepositoryImpl @Inject
constructor(@Named("OpenDota") private val api: ServerApi, private val db: AppDatabase) : OpenDotaRepository {
    /**
     * Database function
     */
    override fun getGames(): DataSource.Factory<Int, Game> = db.gameDao().getGames()

    /**
     * Database function
     */
    override suspend fun saveGames(listGames: ArrayList<Game>): List<Long> {
        return db.gameDao().saveGames(listGames)
    }

    /**
     * Database function
     */
    override suspend fun saveHeroes(listHeroes: ArrayList<Hero>) {
        db.heroDao().insertHeroes(listHeroes)
    }

    /**
     * Database function
     */
    override fun getHeroes(): DataSource.Factory<Int, Hero> = db.heroDao().getHeroes()

    /**
     * GET request.
     * Receive Game Response that contains 100 matches by default
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a Response<ArrayList<Game>>.
     */
    override suspend fun getMatches(id32: Int): Response<ArrayList<Game>> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ПРОШЕДШИЕ ИГРЫ")
        return api.getGames(id32)
    }

    /**
     * GET request.
     * Receive detail Game
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a Response<GameDetail>.
     */
    override suspend fun getGameDetail(id: Long): Response<GameDetail> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА КОНКРЕТНУЮ ИГРУ")
        return api.getGameDetail(id)
    }

    /**
     * GET request.
     * Receive Heroes played
     */
    override suspend fun fetchHeroes(id32: Int): Response<ArrayList<Hero>> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ГЕРОЕВ")
        return api.getHeroes(id32)
    }
}