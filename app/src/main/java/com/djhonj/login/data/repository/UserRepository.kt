package com.djhonj.login.data.repository

import com.djhonj.login.data.datasource.ILocalDataSource
import com.djhonj.login.domain.User

class UserRepository (private val localDataSource: ILocalDataSource) {
    suspend fun getAllUser(): List<User> = localDataSource.getAllUser()
    suspend fun createUser(user: User) = localDataSource.insertUser(user)
}