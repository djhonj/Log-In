package com.djhonj.login.framework.data.database

import com.djhonj.login.data.datasource.ILocalDataSource
import com.djhonj.login.domain.User
import com.djhonj.login.framework.data.toDomainUser
import com.djhonj.login.framework.data.toRoomUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: AppDbRoom) : ILocalDataSource {
    private val userDao = db.userDao()

    override suspend fun getAllUser(): List<User> {
        return withContext(Dispatchers.IO) { userDao.getUserAll().map { it.toDomainUser() } }
    }

    override suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insertUser(user.toRoomUser())
        }
    }

    override suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.updateUser(user.toRoomUser())
        }
    }
}