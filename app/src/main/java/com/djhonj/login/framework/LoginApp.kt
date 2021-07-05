package com.djhonj.login.framework

import android.app.Application
import androidx.room.Room
import com.djhonj.login.framework.data.database.AppDbRoom

class LoginApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AppDbRoom.build(this)
    }
}