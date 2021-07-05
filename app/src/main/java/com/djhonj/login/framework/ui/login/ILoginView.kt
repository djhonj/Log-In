package com.djhonj.login.framework.ui.login

import com.djhonj.login.domain.User

interface ILoginView {
    fun startSession(user: User)
    fun showMessage(message: String)
}