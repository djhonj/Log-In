package com.djhonj.login.framework.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.djhonj.login.framework.ui.login.LoginActivity
import com.djhonj.login.framework.data.database.User
import com.djhonj.login.databinding.ActivityMainBinding
import com.djhonj.login.framework.LoginApp
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        runBlocking {
            user = getUser(intent.extras?.getString("userName")!!)
        }

        if (user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.tvText.setText("Bienvenido ${user.name}!!!")

        binding.buttonClose.setOnClickListener {
            closeSession(user)
        }
    }

    private suspend fun getUser(userName: String): User? {
        //return LoginApp.dbRoom.userDao().getUser(userName)
        return null
    }

    private fun closeSession(user: User) {
        lifecycleScope.launch {
            //LoginApp.dbRoom.userDao().updateUser(user.apply { session = false })
            //LoginApp.dbRoom.userDao().getUser(user.userName)
        }

        startActivity(Intent(this, LoginActivity::class.java))
    }
}

