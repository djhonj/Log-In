package com.djhonj.login.framework

import android.app.Application
import androidx.room.Room
import com.djhonj.login.framework.data.database.AppDbRoom

class LoginApp: Application() {
    companion object {
        lateinit var db: AppDbRoom
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AppDbRoom::class.java, "app-login").build()
    }
}