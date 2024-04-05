package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding


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
            showSnackBar("회원가입이 완료되었습니다.")

            intent.putExtra("id", binding.edtSignUpId.text.toString())
            intent.putExtra("pw", binding.edtSignUpPw.text.toString())
            intent.putExtra("nickname", binding.edtSignUpNickname.text.toString())
            intent.putExtra("mbti", binding.edtSignUpMbti.text.toString())
            setResult(RESULT_OK, intent)
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