package com.djhonj.login.domain

data class User (
    var id: Int,
    var name: String,
    var userName: String,
    var password: String,
    var session: Boolean
)