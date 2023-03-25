package com.djhonj.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.djhonj.login.databinding.ActivityMainBinding
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

    private suspend fun getUser(userName: String): User {
        return App.dbRoom.userDao().getUser(userName)
    }

    private fun closeSession(user: User) {
        lifecycleScope.launch {
            App.dbRoom.userDao().updateUser(user.apply { session = false })
            App.dbRoom.userDao().getUser(user.userName!!)
        }

        startActivity(Intent(this, LoginActivity::class.java))
    }
}

