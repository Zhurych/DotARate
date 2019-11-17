package com.ez.dotarate.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ez.dotarate.Event
import com.ez.dotarate.model.User
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
     * We don’t even need to make a method to get the
     */
//    val data = liveData(Dispatchers.IO) {
//        val user = repository.getUser(id)
//        emit(user)
//    }

    val data = MutableLiveData<User>()
    val errorLiveData = MutableLiveData<Event<String>>()

    fun getUser(id: Long) {
        viewModelScope.launch(IO) {
            try {
                val response = repository.getUser(id)

                if (response.isSuccessful) {
                    data.postValue(response.body())

                } else {
                    errorLiveData.postValue(Event(response.errorBody().toString()))
                }
            } catch (e: UnknownHostException) {
                errorLiveData.postValue(Event("ZALUPA"))
            } catch (e: TimeoutException) {
                errorLiveData.postValue(Event("Плохое соединение. Попробуйте позже"))
            } catch (e: SocketTimeoutException) {
                errorLiveData.postValue(Event("Плохое соединение. Попробуйте позже"))
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}