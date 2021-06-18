package com.djhonj.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.djhonj.login.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        user = bundle?.getSerializable("user") as User

        if (user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.tvText.setText("Bienvenido ${user!!.name}!!!")

        binding.buttonClose.setOnClickListener {
            closeSession(user)
        }
    }

    private fun closeSession(user: User) {
        lifecycleScope.launch {
            App.dbRoom.userDao().updateUser(user.apply { session = false })
            App.dbRoom.userDao().getUser(user.userName)
        }

        startActivity(Intent(this, LoginActivity::class.java))
    }
}