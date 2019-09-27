package com.ez.dotarate.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.database.UserId
import com.ez.dotarate.model.repository.UserRepositoryImpl
import java.util.concurrent.Executors

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.invoke(getApplication())

    private val repository = UserRepositoryImpl()

    private val executor = Executors.newSingleThreadExecutor()

    val data: MutableLiveData<UserId> = MutableLiveData()

    fun getUser() {
        executor.execute {
            val user = repository.getUserId(db.userIdDao())
            data.postValue(user)
        }
    }
}