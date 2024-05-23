package com.sopt.now.compose.ui.signUp

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.model.RequestSignUpDto
import com.sopt.now.compose.data.model.ResponseSignUpDto
import com.sopt.now.compose.data.model.SignUpState
import com.sopt.now.compose.data.module.ServicePool
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _signUpState = MutableStateFlow(SignUpState(isSuccess = false, message = ""))
    val signUpState = _signUpState.asStateFlow()

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
                        _signUpState.update {
                            SignUpState(
                                isSuccess = true,
                                message = "가입된 유저 아이디는 $userId"
                            )
                        }
                    } else {
                        val error = response.code()
                        _signUpState.update {
                            SignUpState(
                                isSuccess = false,
                                message = "회원가입 실패 : $error"
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                    _signUpState.update {
                        SignUpState(isSuccess = false, message = "서버 에러")
                    }
                    Log.d("SignUp", "${t.message}")
                }
            },
        )
    }
}