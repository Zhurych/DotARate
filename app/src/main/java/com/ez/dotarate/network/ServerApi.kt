package com.ez.dotarate.network

import com.ez.dotarate.database.Game
import com.ez.dotarate.database.Hero
import com.ez.dotarate.database.SearchUser
import com.ez.dotarate.model.GameDetail
import com.ez.dotarate.model.UserResponse
import com.ez.dotarate.model.WinsAndLosses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Интерфейс для работы с Retrofit
 * содержит методы запроса
 */
interface ServerApi {
    @GET("api/players/{account_id}")
    suspend fun getUser(@Path("account_id") id: Int): Response<UserResponse>

    @GET("api/players/{steamID32}/matches/?limit=100")
    suspend fun getGames(@Path("steamID32") id: Int): Response<ArrayList<Game>>

    @GET("api/players/{steamID32}/matches")
    suspend fun fetchGames(
        @Path("steamID32") id: Int,
        @Query("offset") loadPosition: Int,
        @Query("limit") limit: Int
    ): Response<ArrayList<Game>>

    @GET("api/matches/{matchID}")
    suspend fun getGameDetail(@Path("matchID") id: Long): Response<GameDetail>

    @GET("api/players/{account_id}/wl")
    suspend fun getWinsAndLosses(@Path("account_id") id: Int): Response<WinsAndLosses>

    @GET("api/players/{account_id}/heroes")
    suspend fun fetchHeroes(@Path("account_id") id: Int): Response<ArrayList<Hero>>

    @GET("api/search")
    suspend fun searchUsersByName(@Query("q") name: String): Response<ArrayList<SearchUser>>

    @GET("api/proPlayers")
    suspend fun getTopPlayers(): Response<ArrayList<SearchUser>>
}