package com.ez.dotarate.network

import com.ez.dotarate.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Интерфейс для работы с Retrofit
 * содержит методы запроса
 */
interface ServerApi {
    @GET("ISteamUser/GetPlayerSummaries/v0002/?")
    fun getUser(@Query("key") key: String, @Query("steamids") id: Long): Call<User>
}