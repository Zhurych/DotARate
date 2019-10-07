package com.ez.dotarate.model.repository

import com.ez.dotarate.constants.BASE_URL
import com.ez.dotarate.constants.STEAM_API_KEY
import com.ez.dotarate.database.UserId
import com.ez.dotarate.database.UserIdDao
import com.ez.dotarate.network.ServerApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserRepositoryImpl : UserRepository {

    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    private val api: ServerApi = retrofit.create(ServerApi::class.java)

    /**
     * This is a "regular" suspending function, which means the caller must
     * be in a coroutine. The repository is not responsible for starting or
     * stopping coroutines since it doesn't have a natural lifecycle to cancel
     * unnecessary work.
     *
     * This *may* be called from Dispatchers.Main and is main-safe because
     * Room will take care of main-safety for us.
     */
    override suspend fun getUserId(dao: UserIdDao) = dao.getId()

    override suspend fun saveUserId(dao: UserIdDao, userId: UserId) {
        dao.saveId(userId)
    }

    /**
     * GET request.
     * Receive User Data
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a User object.
     */
    override suspend fun getUser(id: Long) = api.getUser(getK(), id)

    /**
     * Decode Steam Api Key.
     */
    private fun getK(): String {
        val key = STEAM_API_KEY.split(" ")

        val result = StringBuilder()
        for (i in key) {
            val int = i.toInt() shr 2
            result.append(int.toChar())
        }
        return result.toString()
    }

    override suspend fun logout(dao: UserIdDao) {
        dao.deleteUser()
    }
}