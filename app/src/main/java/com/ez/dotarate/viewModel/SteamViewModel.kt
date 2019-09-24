package com.ez.dotarate.viewModel

import androidx.lifecycle.ViewModel
import com.ez.dotarate.model.repository.UserRepository
import com.ez.dotarate.model.repository.UserRepositoryImpl

class SteamViewModel(repository: UserRepository) : ViewModel() {

    private val repository: UserRepository = UserRepositoryImpl()

    fun saveId() {
        // TODO сделать асинхронным
    }

}