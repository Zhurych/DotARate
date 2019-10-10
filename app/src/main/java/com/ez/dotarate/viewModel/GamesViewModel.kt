package com.ez.dotarate.viewModel

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.database.Games
import com.ez.dotarate.model.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GamesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserRepositoryImpl()

    var liveGames: LiveData<PagedList<Games>>

    private val db = AppDatabase.invoke(getApplication())

    // У LivePagedListBuilder есть метод setInitialLoadKey.
    // Он задает начальное значение для первоначальной подгрузки
    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        liveGames =
            LivePagedListBuilder<Int, Games>(db.gamesDao().getGames(), config).setInitialLoadKey(10)
                .build()
    }

    fun getGames(id32: Int) {
        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                //repository.getMatches(id32)
            }

            //repository.saveGames(db.gamesDao(), list)
        }
    }
}