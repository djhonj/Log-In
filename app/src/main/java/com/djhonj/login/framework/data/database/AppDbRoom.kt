package com.djhonj.login.framework.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.djhonj.login.framework.data.database.IUserDao
import com.djhonj.login.framework.data.database.User

@Database(
    version = 1,
    entities = arrayOf(User::class)
)
abstract class AppDbRoom : RoomDatabase() {
    companion object {
        //fun build(context: Context) = Room.databaseBuilder(context, AppDbRoom::class.java, "login-db").build()
    }

    abstract fun userDao(): IUserDao
}