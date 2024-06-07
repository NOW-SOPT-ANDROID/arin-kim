package com.sopt.now.compose.ui.signIn

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.model.RequestSignInDto
import com.sopt.now.compose.data.model.ResponseSignInDto
import com.sopt.now.compose.data.model.SignInState
import com.sopt.now.compose.data.module.ServicePool
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _signInState = MutableStateFlow(SignInState(isSuccess = false, message = ""))
    val signInState = _signInState.asStateFlow()

    private val _id = MutableStateFlow("")
    val id = _id.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun updateId(newId: String) {
        _id.value = newId
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }


    fun signIn(request: RequestSignInDto) {
        authService.signIn(request).enqueue(
            object : Callback<ResponseSignInDto> {
                override fun onResponse(
                    call: Call<ResponseSignInDto>,
                    response: Response<ResponseSignInDto>,
                ) {
                    if (response.isSuccessful) {
                        val userId = response.headers()["location"]

                        _signInState.update {
                            SignInState(
                                isSuccess = true,
                                message = "유저 아이디는 $userId"
                            )
                        }
                    } else {
                        val error = response.code()
                        _signInState.update {
                            SignInState(
                                isSuccess = false,
                                message = "로그인 실패 : $error"
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseSignInDto>, t: Throwable) {
                    _signInState.update { SignInState(isSuccess = false, message = "서버 에러") }
                }
            }
        )
    }
}
