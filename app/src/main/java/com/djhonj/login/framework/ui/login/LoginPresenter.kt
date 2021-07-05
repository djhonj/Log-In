package com.djhonj.login.framework.ui.login

import androidx.lifecycle.lifecycleScope
import com.djhonj.login.domain.User
import com.djhonj.login.framework.LoginApp
import com.djhonj.login.framework.data.toDomainUser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter (private val view: ILoginView) {
    //valida si hay un usuario con session iniciada
    fun validateSession(username: String, password: String) {
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            view.showMessage("Completar todos los campos")
        } else {
            var user: User // repository

            val job = GlobalScope.launch {
                val users = LoginApp.dbRoom.userDao().getUserAll()
                if (userParam == null) {
                    user = users.find { u -> u.session == true }

                    if  (user != null) {
                        initSession(user!!)
                    }
                } else {
                    user = users.find {
                        userParam.userName == it.userName && userParam.password == it.password
                    }

                    if (user == null) {
                        view.showMessage("Credenciales para inicio de sesion incorrectas")
                    } else {
                        view.startSession(user)
                    }
                }
            }
            
        }
    }
}