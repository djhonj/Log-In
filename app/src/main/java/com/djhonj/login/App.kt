package com.djhonj.login

import android.app.Application
import android.widget.Toast
import androidx.room.Room

class App: Application() {
    companion object {
        lateinit var dbRoom: AppDbRoom
    }

    override fun onCreate() {
        super.onCreate()
        dbRoom = Room.databaseBuilder(this, AppDbRoom::class.java, "app-login").build()
    }
}