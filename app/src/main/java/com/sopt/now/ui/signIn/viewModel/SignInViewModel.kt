package com.sopt.now.ui.signIn.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sopt.now.data.model.RequestSignInDto
import com.sopt.now.data.model.ResponseSignInDto
import com.sopt.now.data.model.SignInState
import com.sopt.now.data.module.ServicePool
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

    fun signIn(request: RequestSignInDto) {
        authService.singIn(request).enqueue(
            object : Callback<ResponseSignInDto> {
                override fun onResponse(
                    call: Call<ResponseSignInDto>,
                    response: Response<ResponseSignInDto>,
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        val userId = response.headers()["location"]
                        _signInState.update {
                            SignInState(
                                isSuccess = true,
                                message = "유저 아이디는 $userId"
                            )
                        }
                        Log.d("SignIn", "$data")
                    } else {
                        val data = response.body()
                        val error = response.code()
                        _signInState.update {
                            SignInState(
                                isSuccess = false,
                                message = "로그인 실패 : $error"
                            )
                        }
                        Log.d("SignIn", "${data?.message}")
                    }
                }

                override fun onFailure(call: Call<ResponseSignInDto>, t: Throwable) {
                    _signInState.update { SignInState(isSuccess = false, message = "서버 에러") }
                }
            }
        )
    }
}