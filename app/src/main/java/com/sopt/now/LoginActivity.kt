package com.sopt.now

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private var isCheckLogin = false
    private var savedId = ""
    private var savedPw = ""
    private var savedNickname = ""
    private var savedMbti = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startForResult =
            registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    savedId = result.data?.getStringExtra("id") ?: "d"
                    savedPw = result.data?.getStringExtra("pw") ?: "d"
                }
            }

        binding.btnLogin.setOnClickListener {
            val id = binding.edtLoginId.text.toString()
            val pw = binding.edtLoginPw.text.toString()
            Log.d("로그인", "$id, $savedId, $pw, $savedPw")
            isCheckLogin = checkLogin(id, pw, savedId, savedPw)
            if (isCheckLogin) {
                showSnackBar("로그인 성공!")
                Log.d("로그인", savedId)
                moveToMain()
            } else {
                showSnackBar("로그인에 실패하였습니다.")
            }
        }

        binding.btnSignUp.setOnClickListener {
            moveToSignUp()
        }
    }

    private fun moveToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startForResult.launch(intent)
    }

    private fun moveToMain() {
        startForResult.launch(Intent(this, MainActivity::class.java))
    }

    private fun showSnackBar(text: String = "") {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun checkLogin(id: String, pw: String, savedId: String, savedPw: String): Boolean {
        return id.isNotEmpty() && pw.isNotEmpty() && id == savedId && pw == savedPw
    }
}