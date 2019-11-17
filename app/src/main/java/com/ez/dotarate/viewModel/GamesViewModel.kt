package com.ez.dotarate.viewModel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.dotarate.Event
import com.ez.dotarate.database.Game
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


class GamesViewModel @Inject
constructor(
    application: Application, private val repository: OpenDotaRepositoryImpl
) : AndroidViewModel(application) {

    val isGamesEmpty = ObservableBoolean(false)

    val isLoaded = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Event<String>>()

    var liveGame: LiveData<PagedList<Game>>

    // У LivePagedListBuilder есть метод setInitialLoadKey.
    // Он задает начальное значение для первоначальной подгрузки
    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        liveGame =
            LivePagedListBuilder<Int, Game>(repository.getGames(), config).setInitialLoadKey(15)
                .build()
    }

    fun getGames(id32: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getMatches(id32)
                }
                if (response.isSuccessful) {
                    isLoaded.postValue(true)
                } else {
                    isLoaded.postValue(true)
                }
            } catch (e: UnknownHostException) {
                isLoaded.postValue(true)
                errorLiveData.value = Event("Отсутствует интернет соединение")
            } catch (e: SocketTimeoutException) {
                isLoaded.postValue(true)
                errorLiveData.value = Event("Плохое соединение. Попробуйте позже")
            }
        }
    }
}