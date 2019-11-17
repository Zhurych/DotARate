package com.ez.dotarate.viewModel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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
                }
            } catch (e: UnknownHostException) {
                errorLiveData.postValue("Нет интернета")
            } catch (e: SocketTimeoutException) {
                // Возникает при плохом соединении с интернетом.
                // Когда заканчивается время (timeout) для запроса к серверу.
                // Timeout устанавливается при настройке запроса
                errorLiveData.postValue("Плохое качество соединения с интернетом. Попробуйте позже")
            }
        }
    }
}