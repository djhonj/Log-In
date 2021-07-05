package com.djhonj.login.framework.ui.main

import com.djhonj.login.data.repository.UserRepository
import com.djhonj.login.domain.User
import com.djhonj.login.framework.LoginApp
import com.djhonj.login.framework.data.database.RoomDataSource
import com.djhonj.login.usecases.CloseSession
import com.djhonj.login.usecases.GetAllUser
import kotlinx.coroutines.*

class MainPresenter(private val view: IMainView) {
    fun closeSession(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            val job = launch {
                CloseSession(UserRepository(RoomDataSource(LoginApp.db))).invoke(user.apply { session = false })
            }

            job.join()

            withContext(Dispatchers.Main) {
                view.startActivity()
            }
        }
    }

    fun getUser(userName: String): User? {
        var user: User? = null

        runBlocking {
            user = GetAllUser(UserRepository(RoomDataSource(LoginApp.db))).invoke().find {
                userName == it.userName
            }


        }

        if (user == null) {
            view.startActivity()
        }

        return user
    }
}