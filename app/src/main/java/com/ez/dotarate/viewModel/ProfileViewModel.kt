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

    /**
     * LiveData’s building block already provides a Coroutine Scope where to call
     *  suspend functions like the one in our repository.
     * So let’s use that with the IO Dispatcher since we’re making a network call.
     * The building block will automatically switch to the UI thread to update LiveData value when needed.
     * We don’t even need to make a method to get the User ID Key
     */
//    val liveUser = liveData {
//        emit(repository.getUser())
//    }

    val liveUser = repository.getUser()

    val isDataReceived = ObservableBoolean(false)
    val isNeedPositionToStartGames = ObservableBoolean(false)
    val isNeedPositionToStartMph = ObservableBoolean(false)

    val userResponseLiveData = MutableLiveData<UserResponse>()
    val wlLiveData = MutableLiveData<WinsAndLosses>()
    val errorLiveData = MutableLiveData<Event<String>>()
    val isNeedRefresh = MutableLiveData<Boolean>()

    fun getUserResponse(id: Long) {
        viewModelScope.launch(IO) {
            try {
                val response = repository.getUserResponse(id)

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

    fun getWinsAndLosses(id: Long) {
        viewModelScope.launch(IO) {
            try {
                val response = repository.getWinsAndLosses(id)

                if (response.isSuccessful) {
                    wlLiveData.postValue(response.body())
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