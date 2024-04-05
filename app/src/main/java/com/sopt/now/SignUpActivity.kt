package com.sopt.now

import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    companion object {
        const val MIN_LENGTH_LOGIN = 6
        const val MAX_LENGTH_LOGIN = 10
        const val MIN_LENGTH_PASSWORD = 8
        const val MAX_LENGTH_PASSWORD = 12
        const val MBTI_LENGTH = 4
    }

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSignUpButton()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun setSignUpButton() {
        binding.btnSignUp.setOnClickListener {
            if (isInputValid()) {
                handleValidInput()
            } else {
                showToastMessage("회원 정보를 모두 입력해주세요.")
            }
        }
    }

    private fun isInputValid(): Boolean {
        return isIdValid() && isPwValid() && isNicknameValid() && isMbtiValid()
    }

    private fun isIdValid(): Boolean {
        val idText = binding.edtSignUpId.text.toString()
        return idText.isNotBlank() && idText.length in MIN_LENGTH_LOGIN..MAX_LENGTH_LOGIN
    }

    private fun isPwValid(): Boolean {
        val pwText = binding.edtSignUpPw.text.toString()
        return pwText.isNotBlank() && pwText.length in MIN_LENGTH_PASSWORD..MAX_LENGTH_PASSWORD
    }

    private fun isNicknameValid(): Boolean {
        val nicknameText = binding.edtSignUpNickname.text.toString()
        return nicknameText.isNotBlank() && !nicknameText.contains(" ")
    }

    private fun isMbtiValid(): Boolean {
        val mbtiText = binding.edtSignUpMbti.text.toString()
        return mbtiText.isNotBlank() && mbtiText.length == MBTI_LENGTH
    }

    private fun handleValidInput() {
        showToastMessage("회원가입이 완료되었습니다.")

        intent.apply {
            putExtra("id", binding.edtSignUpId.text.toString())
            putExtra("pw", binding.edtSignUpPw.text.toString())
            putExtra("nickname", binding.edtSignUpNickname.text.toString())
            putExtra("mbti", binding.edtSignUpMbti.text.toString())
        }

        setResult(RESULT_OK, intent)
        finish()
    }

    private fun showToastMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
