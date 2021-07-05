package com.djhonj.login.data.datasource

import com.djhonj.login.domain.User

interface ILocalDataSource {
    suspend fun getUserAll(): List<User>
//    fun getUser(userName: String): User
//    fun getUser(userName: String, password: String): User
//    fun insertUser(user: User)
//    fun updateUser(user: User)
}