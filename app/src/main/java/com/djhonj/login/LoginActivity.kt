package com.djhonj.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.djhonj.login.databinding.ActivityLoginBinding
import com.djhonj.login.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validateSession(null)

        binding.buttonLogin.setOnClickListener {
            if (binding.etUser.text.isNullOrEmpty() || binding.etPassword.text.isNullOrEmpty()) {
                toast("Completar todos los campos")
            } else {
                validateSession(User(0, "", binding.etUser.text.toString(), binding.etPassword.text.toString()))
            }
        }

        binding.buttonSingup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validateSession(userParam: User?) {
        var user: User?

        lifecycleScope.launch {
            val users = App.dbRoom.userDao().getUserAll()
            if (userParam == null) {
                user = users.find { u -> u.session == true }

                if  (user != null) {
                    initSession(user!!)
                }
            } else {
                user = users.find {
                    userParam.userName == it.userName && userParam.password == it.password
                }

                if (user == null) {
                    toast("Credenciales para inicio de sesion incorrectas")
                } else {
                    App.dbRoom.userDao().updateUser(user!!.apply { session = true })
                    initSession(user!!)
                }
            }
        }
    }

    private fun initSession(user: User) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("userName", user.userName)
        }

        startActivity(intent)
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}