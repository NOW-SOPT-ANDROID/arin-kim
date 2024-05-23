package com.sopt.now.compose.ui.signUp

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.model.RequestSignUpDto
import com.sopt.now.compose.data.model.ResponseSignUpDto
import com.sopt.now.compose.data.module.ServicePool
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _signUpEvent = MutableStateFlow<SignUpSideEffect?>(null)
    val signUpEvent = _signUpEvent.asStateFlow()

    private val _id = MutableStateFlow("")
    val id = _id.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _nickname = MutableStateFlow("")
    val nickname = _nickname.asStateFlow()

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()

    fun updateId(id: String) {
        _id.value = id
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun updateNickname(nickname: String) {
        _nickname.value = nickname
    }

    fun updatePhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }


    fun signUp(request: RequestSignUpDto) {
        authService.signUp(request).enqueue(
            object : Callback<ResponseSignUpDto> {
                override fun onResponse(
                    call: Call<ResponseSignUpDto>,
                    response: Response<ResponseSignUpDto>,
                ) {
                    if (response.isSuccessful) {
                        val userId = response.headers()["location"]
                        _signUpEvent.value = SignUpSideEffect.Success("가입된 유저 아이디는 $userId")
                    } else {
                        val error = response.code()
                        _signUpEvent.value = SignUpSideEffect.Error("회원가입 실패 : $error")
                    }
                }

                override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                    _signUpEvent.value = SignUpSideEffect.Error("서버 에러: ${t.message}")
                }
            },
        )
    }

    companion object {
        const val MIN_LENGTH_LOGIN = 6
        const val MAX_LENGTH_LOGIN = 10
        const val MIN_LENGTH_PASSWORD = 8
        const val MAX_LENGTH_PASSWORD = 12
    }
}