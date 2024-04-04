package com.sopt.now

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moveToSignUp()
    }

    private fun moveToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)

        binding.btnSignUp.setOnClickListener {
            startActivity(intent)
            finish()
        }
    }
}