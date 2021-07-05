package com.djhonj.login.data.repository

import com.djhonj.login.data.datasource.ILocalDataSource
import com.djhonj.login.domain.User

class UserRepository (private val localDataSource: ILocalDataSource) {
    suspend fun getUserAll(): List<User> = localDataSource.getUserAll()
}