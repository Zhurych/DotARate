package com.ez.dotarate.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ez.dotarate.customClasses.Event
import com.ez.dotarate.dataSource.TopPlayersDataSource
import com.ez.dotarate.database.SearchUser
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class SearchViewModel
@Inject constructor(
    application: Application, private val repository: OpenDotaRepositoryImpl
) : AndroidViewModel(application) {

    var isExistedFragment = false
    val liveTopPlayers = MutableLiveData<ArrayList<SearchUser>>()

    fun getTopPlayers() {
        viewModelScope.launch(IO) {
            try {
                val response = repository.getTopPlayers()

                Log.d("MyLogs", "SearchViewModel. РЕЗУЛЬТАТ ЗАПРОСА = ${response.body()}")
                if (response.isSuccessful) {
                    liveTopPlayers.postValue(response.body()!!)
                }
            } catch (e: UnknownHostException) {

            } catch (e: SocketTimeoutException) {

            }
        }
    }
}