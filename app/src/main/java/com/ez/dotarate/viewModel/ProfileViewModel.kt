package com.ez.dotarate.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.model.User
import com.ez.dotarate.model.repository.UserRepositoryImpl
import java.util.concurrent.Executors


class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserRepositoryImpl()

    private val db = AppDatabase.invoke(getApplication())

    private val executor = Executors.newSingleThreadExecutor()

    val data: MutableLiveData<User> = MutableLiveData()

    fun getUser(id: Long) {
        repository.getUser(data, id)
    }

    fun logout() {
        executor.execute { repository.logout(db.userIdDao()) }
    }
}