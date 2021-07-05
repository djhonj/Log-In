package com.djhonj.login.framework.data.database

import android.content.Context
import com.djhonj.login.data.datasource.ILocalDataSource
import com.djhonj.login.domain.User
import com.djhonj.login.framework.data.toDomainUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: AppDbRoom): ILocalDataSource {
    private val userDao = db.userDao()

    override suspend fun getUserAll(): List<User> {
        return withContext(Dispatchers.IO) { userDao.getUserAll().map { it.toDomainUser() } }
    }
}