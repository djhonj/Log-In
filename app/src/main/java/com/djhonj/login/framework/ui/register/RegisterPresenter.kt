package com.djhonj.login.framework.ui.register

import com.djhonj.login.data.repository.UserRepository
import com.djhonj.login.framework.data.database.RoomDataSource
import com.djhonj.login.usecases.CreateUser
import com.djhonj.login.usecases.GetAllUser
import com.djhonj.login.domain.User
import com.djhonj.login.framework.LoginApp
import com.djhonj.login.framework.ui.common.IView
import kotlinx.coroutines.*

class RegisterPresenter(private val view: IView) {
    fun validateUser(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            val users = GetAllUser(UserRepository(RoomDataSource(LoginApp.db))).invoke()

            withContext(Dispatchers.Main) {
                if (users.size >= 1) {
                    if (users.find { it.userName == user.userName } != null) {
                        view.showMessage("Usuario ya existe")
                    } else {
                        createAccount(user)
                    }
                } else {
                    createAccount(user)
                }
            }
        }
    }

    private fun createAccount(user: User)  {
        runBlocking {
            CreateUser(UserRepository(RoomDataSource(LoginApp.db))).invoke(user)
        }

        view.startActivity(user)
    }
}