package com.djhonj.login

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,  val name: String, val userName: String, val password: String, var session: Boolean = false
): Serializable