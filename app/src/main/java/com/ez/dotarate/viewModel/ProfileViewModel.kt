package com.ez.dotarate.viewModel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.ez.dotarate.customClasses.Event
import com.ez.dotarate.database.User
import com.ez.dotarate.model.UserResponse
import com.ez.dotarate.model.WinsAndLosses
import com.ez.dotarate.model.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject


class ProfileViewModel @Inject
constructor(
    application: Application, private val repository: UserRepositoryImpl
) : AndroidViewModel(application) {

    val liveUser = repository.getUser()

    val isDataReceived = ObservableBoolean(false)
    val isNeedPositionToStartGames = ObservableBoolean(false)
    val isNeedPositionToStartMph = ObservableBoolean(false)

    val userResponseLiveData = MutableLiveData<UserResponse>()
    val liveWinsAndLosses = MutableLiveData<WinsAndLosses>()
    val errorLiveData = MutableLiveData<Event<String>>()

    fun getUserResponse(id32: Int) {
        viewModelScope.launch(IO) {
            try {
                val response = repository.getUserResponse(id32)

                if (response.isSuccessful) {
                    userResponseLiveData.postValue(response.body())

                } else {
                    errorLiveData.postValue(Event(response.errorBody().toString()))
                }
            } catch (e: UnknownHostException) {
                errorLiveData.postValue(Event("Exception"))
            } catch (e: TimeoutException) {
                errorLiveData.postValue(Event("Плохое соединение. Попробуйте позже"))
            } catch (e: SocketTimeoutException) {
                errorLiveData.postValue(Event("Плохое соединение. Попробуйте позже"))
            }
        }
    }

    fun getWinsAndLosses(id32: Int) {
        viewModelScope.launch(IO) {
            try {
                val response = repository.getWinsAndLosses(id32)

                if (response.isSuccessful) {
                    liveWinsAndLosses.postValue(response.body())
                } else {
                    errorLiveData.postValue(Event(response.errorBody().toString()))
                }
            } catch (e: UnknownHostException) {
                errorLiveData.postValue(Event("Exception"))
            } catch (e: SocketTimeoutException) {
                errorLiveData.postValue(Event("Плохое соединение. Попробуйте позже"))
            }
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch {
            repository.saveUser(user)
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}