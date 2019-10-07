package com.ez.dotarate.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.model.User
import com.ez.dotarate.model.repository.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserRepositoryImpl()

    private val db = AppDatabase.invoke(getApplication())

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

    fun getUser(id: Long) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                repository.getUser(id)
            }

            data.value = user
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout(db.userIdDao())
        }
    }
}