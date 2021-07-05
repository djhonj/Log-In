package com.djhonj.login.framework.ui.register

import com.djhonj.login.domain.User

interface IRegisterView {
    fun showMessage(message: String)
    fun goActivity(user: User)
}