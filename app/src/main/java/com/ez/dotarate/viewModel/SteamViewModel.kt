package com.ez.dotarate.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.database.UserId
import com.ez.dotarate.model.repository.UserRepository
import com.ez.dotarate.model.repository.UserRepositoryImpl
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class SteamViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository = UserRepositoryImpl()

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    private val db: AppDatabase = AppDatabase.invoke(getApplication())

    fun saveId(id: Long) {
        executor.execute { repository.saveUserId(db.userIdDao(), UserId(id)) }
    }
}