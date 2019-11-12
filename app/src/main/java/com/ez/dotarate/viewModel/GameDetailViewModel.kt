package com.ez.dotarate.viewModel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ez.dotarate.model.GameDetail
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class GameDetailViewModel @Inject
constructor(
    application: Application, private val repository: OpenDotaRepositoryImpl
    ) : AndroidViewModel(application) {

    val liveGame = MutableLiveData<GameDetail>()
    val errorLiveData = MutableLiveData<String>()

    val isLoaded = ObservableBoolean(false)

    fun getGameDetail(id: Long) {
        viewModelScope.launch(IO) {
            try {
                val response = repository.getGameDetail(id)

                //Log.d("MyLogs", "УСПЕШЕЫЙ ЗАПРОС = ${response.body()}")
                if (response.isSuccessful) {
                    liveGame.postValue(response.body())
                } else {
                    errorLiveData.postValue(response.errorBody().toString())
                    Log.d("MyLogs", "ОШИБКА ПРИ ЗАПРОСЕ. ИМЯ ОШИБКИ= ${response.errorBody()}")
                    Log.d("MyLogs", "ОШИБКА ПРИ ЗАПРОСЕ. КОД ОШИБКИ= ${response.code()}")
                }
            } catch (e: UnknownHostException) {
                errorLiveData.postValue("Нет интернета")
                Log.d("MyLogs", "НЕТ ИНТЕРНЕТА = $e")
            }
        }
    }
}