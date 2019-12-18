package com.ez.dotarate.model

import android.util.Log
import androidx.paging.PositionalDataSource
import com.ez.dotarate.database.Game
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class GamesDataSource(
    private val scope: CoroutineScope,
    private val repository: OpenDotaRepositoryImpl,
    private val id32: Int
) : PositionalDataSource<Game>() {

    /**
     * Первоначальная загрузка данных
     */
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Game>) {
        Log.d("MyLogs", "GamesDataSource. loadInitial")
        scope.launch(IO) {
            val response = repository.fetchMatches(
                id32 = id32,
                loadPosition = params.requestedStartPosition,
                limitSize = params.requestedLoadSize
            )
            if (response.isSuccessful) {
                Log.d("MyLogs", "GamesDataSource. Game Response = ${response.body()}")
                response.body()?.let { callback.onResult(it, 0) }
            }
        }
    }

    /**
     * Подгрузка новой порции данных
     */
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Game>) {
        Log.d("MyLogs", "GamesDataSource. loadRange")
        scope.launch(IO) {
            val response = repository.fetchMatches(
                id32 = id32,
                loadPosition = params.startPosition,
                limitSize = params.loadSize
            )
            if (response.isSuccessful) {
                response.body()?.let { callback.onResult(it) }
            }
        }
    }
}