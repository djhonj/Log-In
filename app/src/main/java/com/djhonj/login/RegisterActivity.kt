package com.djhonj.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.djhonj.login.R
import com.djhonj.login.databinding.ActivityRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCreate.setOnClickListener {
            if (binding.etName.text.isEmpty() || binding.etUser.text.isEmpty() || binding.etPassword.text.isEmpty()) {
                toast("Completar todo el formulario")
            } else {
                val name = binding.etName.text.toString()
                val userName = binding.etUser.text.toString()
                val password = binding.etPassword.text.toString()
                val newUser = User(name = name, userName = userName, password = password)

                lifecycleScope.launch {
                    val users: List<User> = App.dbRoom.userDao().getUserAll()
                    if (validateUser(newUser, users)) {
                        toast("Este usuario ya existe.")
                    } else {
                        createAccount(newUser)
                    }
                }
            }
        }
    }

    private fun validateUser(user: User, users: List<User>): Boolean {
        if (users.size >= 1) {
            if (users.find {it.userName == user.userName} != null) return  true
        }

        return false
    }

    private fun createAccount(user: User) {
        lifecycleScope.launch {
            App.dbRoom.userDao().insertUser(user.apply { session = true })
        }

        val intent = Intent(this, MainActivity::class.java).apply { putExtra("userName", user.userName) }
        startActivity(intent)
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}