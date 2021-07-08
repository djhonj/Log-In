package com.djhonj.login.framework.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.djhonj.login.databinding.ActivityLoginBinding
import com.djhonj.login.framework.ui.main.MainActivity
import com.djhonj.login.framework.ui.register.RegisterActivity
import com.djhonj.login.domain.User
import com.djhonj.login.framework.ui.common.IView

class LoginActivity : AppCompatActivity(), IView {
    private lateinit var binding: ActivityLoginBinding
    private val presenterLogin = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val user = User(0, "", binding.etUser.text.toString(), binding.etPassword.text.toString(), false)
            presenterLogin.validateSession(user)
        }

        binding.buttonSingup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun startActivity(user: User) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("userName", user.userName)
        }

        startActivity(intent)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}