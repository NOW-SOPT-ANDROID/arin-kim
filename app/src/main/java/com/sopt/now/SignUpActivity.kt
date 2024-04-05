package com.sopt.now

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var nickname: String
    private lateinit var mbti: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            onClickSignUpButton()
        }
    }

    private fun onClickSignUpButton() {
        if (!isInputValid()) {
            showSnackBar("회원 정보를 모두 입력해주세요.")
        } else {
            id = binding.edtSignUpId.text.toString()
            pw = binding.edtSignUpPw.text.toString()
            nickname = binding.edtSignUpNickname.text.toString()
            mbti = binding.edtSignUpMbti.text.toString()

            showSnackBar("회원가입이 완료되었습니다.")

            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("pw", pw)
            intent.putExtra("nickname", nickname)
            intent.putExtra("mbti", mbti)
            startActivity(intent)
            finish()
        }
    }

    private fun isInputValid(): Boolean {
        return isIdValid() && isPwValid() && isNicknameValid() && isMbtiValid()
    }

    private fun isIdValid(): Boolean {
        val idText = binding.edtSignUpId.text.toString()
        return idText.isNotBlank() && idText.length in 6..10
    }

    private fun isPwValid(): Boolean {
        val pwText = binding.edtSignUpPw.text.toString()
        return pwText.isNotBlank() && pwText.length in 8..12
    }

    private fun isNicknameValid(): Boolean {
        val nicknameText = binding.edtSignUpNickname.text.toString()
        return nicknameText.isNotBlank() && !nicknameText.contains(" ")
    }

    private fun isMbtiValid(): Boolean {
        val mbtiText = binding.edtSignUpMbti.text.toString()
        return mbtiText.isNotBlank() && mbtiText.length == 4
    }

    private fun showSnackBar(text: String = "") {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}