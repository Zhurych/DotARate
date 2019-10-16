package com.ez.dotarate.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.model.User
import com.ez.dotarate.model.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject


class ProfileViewModel @Inject
constructor(
    application: Application, private val repository: UserRepositoryImpl,
    private val db: AppDatabase
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
    val errorLiveData = MutableLiveData<String>()

    fun getUser(id: Long) {
        viewModelScope.launch(IO) {
            try {
                val response = repository.getUser(id)

                if (response.isSuccessful) {
                    data.postValue(response.body())

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

    fun logout() {
        viewModelScope.launch {
            repository.logout(db.userIdDao())
        }
    }
}