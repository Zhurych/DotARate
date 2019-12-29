package com.ez.dotarate.dataSource

import android.util.Log
import androidx.paging.PositionalDataSource
import com.ez.dotarate.database.Hero
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HeroesDataSource(
    private val scope: CoroutineScope,
    private val repository: OpenDotaRepositoryImpl,
    private val id32: Int
) : PositionalDataSource<Hero>() {

    /**
     * Первоначальная загрузка данных
     */
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Hero>) {
        Log.d("MyLogs", "HeroesDataSource. loadInitial")
        scope.launch(IO) {
            val response = repository.fetchHeroes(id32 = id32)
            if (response.isSuccessful) {
                response.body()?.let { callback.onResult(it, 0) }
            }
        }
    }

    /**
     * Подгрузка новой порции данных
     */
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Hero>) {

    }
}