package com.sopt.now.ui.signIn

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sopt.now.data.model.RequestSignInDto
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.ui.main.MainActivity
import com.sopt.now.ui.signIn.viewModel.SignInViewModel
import com.sopt.now.ui.signUp.SignUpActivity
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setCollect()
        setLoginButtonClickListeners()
        setSignUpButtonClickListeners()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }

    private fun setCollect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signInState.collect { signInState ->
                    if (signInState.message.isNotBlank()) {
                        showToastMessage(signInState.message)
                        if (signInState.isSuccess) {
                            moveToMain()
                        }
                    }
                }
            }
        }
    }

    private fun getSignInRequestDto(): RequestSignInDto {
        val id = binding.edtSignInId.text.toString()
        val pw = binding.edtSignInPw.text.toString()

        return RequestSignInDto(
            authenticationId = id,
            password = pw,
        )
    }

    private fun setLoginButtonClickListeners() {
        binding.btnLogin.setOnClickListener {
            viewModel.signIn(getSignInRequestDto())
        }
    }

    private fun setSignUpButtonClickListeners() {
        binding.btnSignUp.setOnClickListener {
            moveToSignUp()
        }
    }


    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }


    private fun moveToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showToastMessage(text: String = "") {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}