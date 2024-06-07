package com.sopt.now.ui.signUp.viewModel

import androidx.lifecycle.ViewModel
import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.data.model.ResponseSignUpDto
import com.sopt.now.data.model.SignUpState
import com.sopt.now.data.module.ServicePool
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
                    _signUpState.update { SignUpState(isSuccess = false, message = "서버 에러") }
                }
            },
        )
    }
}