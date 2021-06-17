package com.djhonj.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.djhonj.login.databinding.ActivityLoginBinding
import com.djhonj.login.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            if (!binding.etUser.text.isNullOrEmpty() || !binding.etPassword.text.isNullOrEmpty()) {
                toast("Completar todos los campos")
            } else {
                
            }
        }

        binding.buttonSingup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    fun validateUser() {

    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}