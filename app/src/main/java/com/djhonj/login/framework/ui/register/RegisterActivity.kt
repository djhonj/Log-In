package com.djhonj.login.framework.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.djhonj.login.domain.User
import com.djhonj.login.databinding.ActivityRegisterBinding
import com.djhonj.login.framework.LoginApp
import com.djhonj.login.framework.data.toRoomUser
import com.djhonj.login.framework.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity(), IRegisterView {
    private lateinit var binding: ActivityRegisterBinding
    private val presenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCreate.setOnClickListener {
            if (binding.etName.text.isEmpty() || binding.etUser.text.isEmpty() || binding.etPassword.text.isEmpty()) {
                showMessage("Completar todo el formulario")
            } else {
                val name = binding.etName.text.toString()
                val userName = binding.etUser.text.toString()
                val password = binding.etPassword.text.toString()
                val newUser = User(
                    0,
                    name = name,
                    userName = userName,
                    password = password,
                    session = false
                )

                presenter.validateUser(newUser)
            }
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun goActivity(user: com.djhonj.login.domain.User) {
        val intent = Intent(this, MainActivity::class.java).apply { putExtra("userName", user.userName) }
        startActivity(intent)
    }
}