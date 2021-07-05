package com.djhonj.login.domain

data class User (
    val id: Int?,
    val name: String?,
    val userName: String?,
    val password: String?,
    var session: Boolean
)