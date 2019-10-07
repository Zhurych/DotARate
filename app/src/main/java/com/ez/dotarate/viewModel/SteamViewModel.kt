package com.ez.dotarate.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.database.UserId
import com.ez.dotarate.model.repository.UserRepository
import com.ez.dotarate.model.repository.UserRepositoryImpl
import kotlinx.coroutines.launch


class SteamViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository = UserRepositoryImpl()

    private val db: AppDatabase = AppDatabase.invoke(getApplication())

    fun saveId(id: Long) {
        viewModelScope.launch {
            repository.saveUserId(db.userIdDao(), UserId(id))
        }
    }
}