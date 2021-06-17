package com.djhonj.login

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = arrayOf(User::class)
)
abstract class AppDbRoom : RoomDatabase() {
    abstract fun userDao(): IUserDao
}