package com.ez.dotarate.network

import com.ez.dotarate.database.Game
import com.ez.dotarate.database.Hero
import com.ez.dotarate.model.GameDetail
import com.ez.dotarate.model.UserResponse
import com.ez.dotarate.model.WinsAndLosses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Интерфейс для работы с Retrofit
 * содержит методы запроса
 */
interface ServerApi {
    @GET("api/players/{account_id}")
    suspend fun getUser(@Path("account_id") id: Long): Response<UserResponse>

    @GET("api/players/{steamID32}/matches/?limit=100")
    suspend fun getGames(@Path("steamID32") id: Int): Response<ArrayList<Game>>

    @GET("api/matches/{matchID}")
    suspend fun getGameDetail(@Path("matchID") id: Long): Response<GameDetail>

    @GET("api/players/{account_id}/wl")
    suspend fun getWinsAndLosses(@Path("account_id") id: Long): Response<WinsAndLosses>

    @GET("api/players/{account_id}/heroes")
    suspend fun getHeroes(@Path("account_id")id: Int): Response<ArrayList<Hero>>
}