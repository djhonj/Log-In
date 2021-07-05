package com.djhonj.login.framework.data

import com.djhonj.login.domain.User
import com.djhonj.login.framework.data.database.User as RoomUser

fun RoomUser.toDomainUser(): User {
    return User(id, name, userName, password,  session)
}

fun User.toRoomUser(): RoomUser {
    return RoomUser(id, name, userName, password, session)
}