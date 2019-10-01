package com.ez.dotarate.model.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ez.dotarate.database.UserId
import com.ez.dotarate.database.UserIdDao
import com.ez.dotarate.model.User
import com.ez.dotarate.network.ServerApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepositoryImpl : UserRepository {

    private val mBaseUrl = "https://api.steampowered.com/"

    private val keyApi = "224 272 200 268 280 220 260 220 268 212 192 228 280 192 280 212 276 192 192 212 208 280 200 224 264 224 268 212 204 224 196 204"

    private val retrofit =
        Retrofit.Builder().baseUrl(mBaseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()

    private val api: ServerApi = retrofit.create(ServerApi::class.java)

    override fun getUserId(dao: UserIdDao) = dao.getId()

    override fun saveUserId(dao: UserIdDao, userId: UserId) {
        dao.saveId(userId)
    }

    override fun getUser(data: MutableLiveData<User>, id: Long) {
        val call = api.getUser(getK(), id)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {

            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    data.postValue(response.body())
                }
            }
        })
    }

    private fun getK(): String {
        val key = keyApi.split(" ")

        val result = StringBuilder()
        for (i in key) {
            val int = i.toInt() shr 2
            result.append(int.toChar())
        }
        return result.toString()
    }
}