package com.ez.dotarate.model.repository

import com.ez.dotarate.BuildConfig
import com.ez.dotarate.constants.BASE_URL
import com.ez.dotarate.constants.BASE_URL_OPENDOTA
import com.ez.dotarate.constants.STEAM_API_KEY
import com.ez.dotarate.database.*
import com.ez.dotarate.network.ServerApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserRepositoryImpl : UserRepository {

    private var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    private val retrofitSteam =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .build()

    private val retrofitOpenDota = Retrofit.Builder().baseUrl(BASE_URL_OPENDOTA)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiSteam: ServerApi = retrofitSteam.create(ServerApi::class.java)
    private val apiOpenDota: ServerApi = retrofitOpenDota.create(ServerApi::class.java)

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

    override suspend fun logout(dao: UserIdDao) {
        dao.deleteUser()
    }

    override suspend fun saveGames(dao: GameDao, listGames: ArrayList<Game>): List<Long> {
        return dao.saveGames(listGames)
    }

    /**
     * GET request.
     * Receive User Data
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a Response<User> object.
     */
    override suspend fun getUser(id: Long) = apiSteam.getUser(getK(), id)

    /**
     * GET request.
     * Receive Game Response that contains 100 matches by default
     * We don’t need to call enqueue() and implement callbacks anymore!
     * But notice, now our repo method is suspend too and returns a Response<ArrayList<Game>>.
     */
    override suspend fun getMatches(id32: Int) = apiOpenDota.getGames(id32)

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