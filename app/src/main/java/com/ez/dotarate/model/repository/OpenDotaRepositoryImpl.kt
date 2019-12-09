package com.ez.dotarate.model.repository

import android.util.Log
import androidx.paging.DataSource
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.database.Game
import com.ez.dotarate.database.Hero
import com.ez.dotarate.model.GameDetail
import com.ez.dotarate.model.GamesDataSource
import com.ez.dotarate.model.HeroesDataSource
import com.ez.dotarate.network.ServerApi
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class OpenDotaRepositoryImpl @Inject
constructor(@Named("OpenDota") private val api: ServerApi, private val db: AppDatabase) :
    OpenDotaRepository {

    private fun createRemoteGamesDataSource(
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Game> {

        return object : DataSource.Factory<Int, Game>() {
            override fun create(): DataSource<Int, Game> {
                return GamesDataSource(
                    scope = scope,
                    repository = this@OpenDotaRepositoryImpl,
                    id32 = id32
                )
            }
        }
    }

    private fun createRemoteHeroesDataSource(
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Hero> {

        return object : DataSource.Factory<Int, Hero>() {
            override fun create(): DataSource<Int, Hero> {
                return HeroesDataSource(
                    scope = scope,
                    repository = this@OpenDotaRepositoryImpl,
                    id32 = id32
                )
            }
        }
    }

    /**
     * @return games DataSource.Factory
     */
    override fun getGamesDataSourceFactory(
        isLocal: Boolean,
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Game> {
        return if (isLocal) db.gameDao().getGames()
        else createRemoteGamesDataSource(scope, id32)
    }

    /**
     * Database function
     */
    override suspend fun saveGames(listGames: ArrayList<Game>): List<Long> {
        return db.gameDao().saveGames(listGames)
    }

    /**
     * Database function
     */
    override suspend fun saveHeroes(listHeroes: ArrayList<Hero>) =
        db.heroDao().insertHeroes(listHeroes)

    /**
     * @return heroes DataSource.Factory
     */
    override fun getHeroesDataSourceFactory(
        isLocal: Boolean,
        scope: CoroutineScope,
        id32: Int
    ): DataSource.Factory<Int, Hero> {
        return if (isLocal) db.heroDao().getHeroes()
        else createRemoteHeroesDataSource(scope, id32)
    }

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
     * Receive Heroes
     */
    override suspend fun fetchHeroes(id32: Int): Response<ArrayList<Hero>> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ГЕРОЕВ")
        return api.fetchHeroes(id32)
    }

    /**
     * GET request.
     * Receive Game Response that contains 16 games
     * @param loadPosition = позиция, с которой требуется загружать игры
     */
    override suspend fun fetchMatches(
        id32: Int,
        loadPosition: Int,
        limitSize: Int
    ): Response<ArrayList<Game>> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС НА ПРОШЕДШИЕ ИГРЫ С ПОЗИЦИИ = $loadPosition")
        return api.fetchGames(id = id32, loadPosition = loadPosition, limit = limitSize)
    }
}