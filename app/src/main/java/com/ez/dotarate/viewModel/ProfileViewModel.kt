package com.ez.dotarate.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ez.dotarate.model.User
import com.ez.dotarate.model.repository.UserRepositoryImpl

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserRepositoryImpl()

    val data: MutableLiveData<User> = MutableLiveData()

    fun getUser(id: Long) {
        repository.getUser(data, id)
    }
}