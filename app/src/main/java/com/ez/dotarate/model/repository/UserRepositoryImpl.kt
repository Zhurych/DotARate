package com.ez.dotarate.model.repository

import com.ez.dotarate.database.UserId
import com.ez.dotarate.database.UserIdDao

class UserRepositoryImpl : UserRepository {

    override fun getUserId(dao: UserIdDao) = dao.getId()

    override fun saveUserId(dao: UserIdDao, userId: UserId) {
        dao.saveId(userId)
    }
}