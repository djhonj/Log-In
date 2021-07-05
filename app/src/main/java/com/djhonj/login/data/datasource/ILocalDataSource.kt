package com.djhonj.login.data.datasource

import com.djhonj.login.domain.User

interface ILocalDataSource {
    suspend fun getAllUser(): List<User>
//    fun getUser(userName: String): User
//    fun getUser(userName: String, password: String): User
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
}