package com.ez.dotarate.viewModel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.database.Game
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject


class GamesViewModel @Inject
constructor(
    application: Application, private val repository: OpenDotaRepositoryImpl,
    private val db: AppDatabase
) : AndroidViewModel(application) {

    val isGamesEmpty = ObservableBoolean(false)

    var liveGame: LiveData<PagedList<Game>>

    // У LivePagedListBuilder есть метод setInitialLoadKey.
    // Он задает начальное значение для первоначальной подгрузки
    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        liveGame =
            LivePagedListBuilder<Int, Game>(db.gameDao().getGames(), config).setInitialLoadKey(15)
                .build()
    }

    fun getGames(id32: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getMatches(id32)
                }
                if (response.isSuccessful) {
                    Log.d("MyLogs", "ЗАПРОС ПРОШЁЛ УСПЕШНО. ЗНАЧЕНИЕ ОТВЕТА = ${response.body()}")
                    val isAccesInsert = repository.saveGames(db.gameDao(), response.body()!!)
                    Log.d("MyLogs", "СКОЛЬКО ВСТАВЛЕНО ЗАПИСЕЙ В БАЗУ ДАННЫХ = $isAccesInsert")
                } else {
                    Log.d("MyLogs", "ОШИБКА ПРИ ЗАПРОСЕ. ИМЯ ОШИБКИ= ${response.errorBody()}")
                    Log.d("MyLogs", "ОШИБКА ПРИ ЗАПРОСЕ. КОД ОШИБКИ= ${response.code()}")
                }
            } catch (e: UnknownHostException) {
                Log.d("MyLogs", "НЕТ ИНТЕРНЕТА = $e")// Получаем при Авиа - режиме
            }
        }
    }
}