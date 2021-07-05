package com.djhonj.login.framework.ui.common

import com.djhonj.login.domain.User

interface IView {
    fun showMessage(message: String)
    fun startActivity(user: User)
}