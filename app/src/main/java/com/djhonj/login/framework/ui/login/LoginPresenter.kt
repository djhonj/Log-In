package com.djhonj.login.framework.ui.login

import android.app.Activity
import android.content.Context
import com.djhonj.login.data.repository.UserRepository
import com.djhonj.login.domain.User
import com.djhonj.login.framework.LoginApp
import com.djhonj.login.framework.data.database.RoomDataSource
import com.djhonj.login.framework.ui.common.IView
import com.djhonj.login.usecases.GetAllUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginPresenter(private val view: IView) {
    fun validateSession(userParam: User) {
        if (userParam.userName.isNullOrEmpty() || userParam.password.isNullOrEmpty()) {
            view.showMessage("Completar todos los campos")
        } else {
            GlobalScope.launch(Dispatchers.IO) {
                val users = GetAllUser(UserRepository(RoomDataSource(LoginApp.db))).invoke()
                val user = users.find {
                    userParam.userName == it.userName && userParam.password == it.password
                }

                withContext(Dispatchers.Main) {
                    if (user == null) {
                        view.showMessage("Credenciales para inicio de sesión incorrectas")
                    } else {
                        view.startActivity(user)
                    }
                }
            }
        }
    }
}