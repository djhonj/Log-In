package com.djhonj.login.framework.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String?,
    val userName: String?,
    val password: String?,
    var session: Boolean = false
)