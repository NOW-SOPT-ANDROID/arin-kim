package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var savedId = ""
        var savedPw = ""

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    savedId = result.data?.getStringExtra("id") ?: ""
                    savedPw = result.data?.getStringExtra("pw") ?: ""
                }
            }


        val id = binding.edtLoginId.text.toString()
        val pw = binding.edtLoginPw.text.toString()

        binding.btnLogin.setOnClickListener {
            if (checkLogin(id, pw, savedId, savedPw)) {
                showSnackBar("로그인 성공!")
                moveToMain()
            } else {
                showSnackBar("로그인에 실패하였습니다.")
            }
        }

        moveToSignUp()
    }

    private fun moveToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        binding.btnSignUp.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showSnackBar(text: String = "") {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun checkLogin(id: String?, pw: String?, savedId: String?, savedPw: String?): Boolean {
        Log.d("Login", savedId.toString())
        Log.d("Login", savedPw.toString())
        return id != null && pw != null && id == savedId && pw == savedPw
    }
}