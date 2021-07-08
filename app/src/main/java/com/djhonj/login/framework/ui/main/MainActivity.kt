package com.djhonj.login.framework.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.djhonj.login.framework.ui.login.LoginActivity
import com.djhonj.login.domain.User
import com.djhonj.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IMainView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.extras?.let{ it.getString("userName")}

        user = presenter.getUser(userName!!)

        binding.tvText.setText("Bienvenido ${user.name} !!")

        binding.buttonClose.setOnClickListener {
            presenter.closeSession(user)
        }
    }

    override fun startActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}

