package com.djhonj.login.framework.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var userName: String,
    var password: String,
    var session: Boolean = false
)