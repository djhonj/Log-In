package com.djhonj.login.framework.ui.main

import com.djhonj.login.data.repository.UserRepository
import com.djhonj.login.domain.User
import com.djhonj.login.framework.LoginApp
import com.djhonj.login.framework.data.database.RoomDataSource
import com.djhonj.login.usecases.CloseSession
import com.djhonj.login.usecases.GetAllUser
import kotlinx.coroutines.*

class MainPresenter(private val view: IMainView) {
    fun closeSession(user: User?) {
        if (user == null) {
            view.startActivity()
        }

        GlobalScope.launch(Dispatchers.IO) {
            launch {
                CloseSession(UserRepository(RoomDataSource(LoginApp.db))).invoke(user!!.apply { session = false })
            }.join()

            withContext(Dispatchers.Main) {
                view.startActivity()
            }
        }
    }

    fun getUser(userName: String): User {
        var user: User? = null

        if (userName.isNullOrEmpty()) {
            view.startActivity()
        }

        runBlocking {
            user = GetAllUser(UserRepository(RoomDataSource(LoginApp.db))).invoke().find {
                userName == it.userName
            }
        }

        if (user == null) {
            view.startActivity()
        }

        return user!!
    }
}