package com.sopt.now

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var isCheckLogin = false
    private var savedId = ""
    private var savedPw = ""
    private var savedNickname = ""
    private var savedMbti = ""

    private val startForResult =
        registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                savedId = result.data?.getStringExtra("id") ?: ""
                savedPw = result.data?.getStringExtra("pw") ?: ""
                savedNickname = result.data?.getStringExtra("nickname") ?: ""
                savedMbti = result.data?.getStringExtra("mbti") ?: ""
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            val id = binding.edtLoginId.text.toString()
            val pw = binding.edtLoginPw.text.toString()
            isCheckLogin = checkLogin(id, pw, savedId, savedPw)
            if (isCheckLogin) {
                showToastMessage("로그인 성공!")
                moveToMain()
            } else {
                showToastMessage("로그인에 실패하였습니다.")
            }
        }

        binding.btnSignUp.setOnClickListener {
            moveToSignUp()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }


    private fun moveToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startForResult.launch(intent)
    }

    private fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("id", savedId)
            putExtra("pw", savedPw)
            putExtra("nickname", savedNickname)
            putExtra("mbti", savedMbti)
        }
        startActivity(intent)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun showToastMessage(text: String = "") {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun checkLogin(id: String, pw: String, savedId: String, savedPw: String): Boolean {
        return id.isNotEmpty() && pw.isNotEmpty() && id == savedId && pw == savedPw
    }
}