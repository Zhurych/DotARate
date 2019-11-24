package com.ez.dotarate.model.repository

import android.util.Log
import com.ez.dotarate.constants.STEAM_API_KEY
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.database.UserId
import com.ez.dotarate.model.User
import com.ez.dotarate.model.WinsAndLosses
import com.ez.dotarate.network.ServerApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named


class UserRepositoryImpl @Inject
constructor(
    @Named("OpenDota") private val api: ServerApi, private val db: AppDatabase
) : UserRepository {

    /**
     * This is a "regular" suspending function, which means the caller must
     * be in a coroutine. The repository is not responsible for starting or
     * stopping coroutines since it doesn't have a natural lifecycle to cancel
     * unnecessary work.
     *
     * This *may* be called from Dispatchers.Main and is main-safe because
     * Room will take care of main-safety for us.
     */
    override suspend fun getUserId() = db.userIdDao().getId()

    override suspend fun saveUserId(userId: UserId) {
        db.userIdDao().saveId(userId)
    }

    override suspend fun logout() {
        db.userIdDao().deleteUser()
        db.gameDao().deleteGames()
    }

    /**
     * GET request.
     * Receive User Data
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a Response<User> object.
     */
    override suspend fun getUser(id: Long): Response<User> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС ПОЛЬЗОВАТЕЛЯ. ID = $id")
        return api.getUser(id)
    }

    /**
     * GET request.
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a Response<WinsAndLosses> object.
     */
    override suspend fun getWinsAndLosses(id: Long): Response<WinsAndLosses> {
        Log.d("MyLogs", "ПОШЁЛ ЗАПРОС ВИНОВ И ЛУЗОВ. ID = $id")
        return api.getWinsAndLosses(id)
    }

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
}