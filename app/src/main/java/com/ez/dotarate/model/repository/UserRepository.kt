package com.ez.dotarate.model.repository

import androidx.lifecycle.MutableLiveData
import com.ez.dotarate.database.UserId
import com.ez.dotarate.database.UserIdDao
import com.ez.dotarate.model.User

interface UserRepository {
    fun saveUserId(dao: UserIdDao, userId: UserId)
    fun getUserId(dao: UserIdDao): UserId?
    fun getUser(data: MutableLiveData<User>, id: Long)
}