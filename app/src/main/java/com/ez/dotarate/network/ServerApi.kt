package com.ez.dotarate.network

import com.ez.dotarate.model.User
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Интерфейс для работы с Retrofit
 * содержит методы запроса
 */
interface ServerApi {
    @GET("ISteamUser/GetPlayerSummaries/v0002/?")
    suspend fun getUser(@Query("key") key: String, @Query("steamids") id: Long): User
}