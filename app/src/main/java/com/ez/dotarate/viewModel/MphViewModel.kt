package com.ez.dotarate.viewModel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.dotarate.database.Hero
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


class MphViewModel @Inject
constructor(
    application: Application, private val repository: OpenDotaRepositoryImpl
) : AndroidViewModel(application) {

    val isHeroesEmpty = ObservableBoolean(false)

    val liveHeroes: LiveData<PagedList<Hero>>

    // У LivePagedListBuilder есть метод setInitialLoadKey.
    // Он задает начальное значение для первоначальной подгрузки
    init {
        val config = PagedList.Config.Builder()
            .setPageSize(12)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(12)
            .setPrefetchDistance(6)
            .build()
        liveHeroes =
            LivePagedListBuilder<Int, Hero>(repository.getHeroes(), config)
                .build()
    }

    fun getHeroes(id32: Int) {
        viewModelScope.launch(IO) {
            try {
                val response = repository.fetchHeroes(id32)

                if (response.isSuccessful) {
                    repository.saveHeroes(response.body()!!)
                }
            } catch (e: UnknownHostException) {

            } catch (e: SocketTimeoutException) {

            }
        }
    }
}