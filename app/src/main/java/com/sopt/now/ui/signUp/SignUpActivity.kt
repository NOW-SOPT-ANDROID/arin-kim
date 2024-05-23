package com.sopt.now.ui.signUp

import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sopt.now.R
import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.ui.signUp.viewModel.SignUpViewModel
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSignUpButton()
        setCollect()
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
            when {
                isInputValid() -> viewModel.signUp(getSignUpRequestDto())
                else -> showToastMessage(R.string.toast_sign_up_fail)
            }
        }
    }

    private fun setCollect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signUpState.collect { signUpState ->
                    if (signUpState.message.isNotBlank()) {
                        if (signUpState.isSuccess) {
                            handleValidInput()
                        }
                    }
                }
            }
        }
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        val id = binding.edtSignUpId.text.toString()
        val password = binding.edtSignUpPw.text.toString()
        val nickname = binding.edtSignUpNickname.text.toString()
        val phoneNumber = binding.edtSignUpPhoneNumber.text.toString()
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phoneNumber
        )
    }

    private fun isInputValid(): Boolean {
        return isIdValid() && isPwValid() && isNicknameValid() && isPhoneNumberValid()
    }

    private fun isIdValid(): Boolean {
        val idText = binding.edtSignUpId.text.toString()
        return idText.isNotBlank() && idText.length in MIN_LENGTH_LOGIN..MAX_LENGTH_LOGIN
    }

    private fun isPwValid(): Boolean {
        val pwText = binding.edtSignUpPw.text.toString()
        return pwText.isNotBlank() && pwText.length >= MIN_LENGTH_PASSWORD
    }

    private fun isNicknameValid(): Boolean {
        val nicknameText = binding.edtSignUpNickname.text.toString()
        return nicknameText.isNotBlank() && !nicknameText.contains(" ")
    }

    private fun isPhoneNumberValid(): Boolean {
        val phoneNumberText = binding.edtSignUpPhoneNumber.text.toString()
        return phoneNumberText.isNotBlank()
    }

    private fun handleValidInput() {
        showToastMessage(R.string.toast_sign_up_success)
        finish()
    }

    private fun showToastMessage(text: Int) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val MIN_LENGTH_LOGIN = 6
        const val MAX_LENGTH_LOGIN = 10
        const val MIN_LENGTH_PASSWORD = 8
    }
}
