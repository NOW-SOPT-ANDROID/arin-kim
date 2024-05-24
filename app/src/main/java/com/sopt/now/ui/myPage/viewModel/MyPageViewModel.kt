package com.sopt.now.ui.myPage.viewModel

import androidx.lifecycle.ViewModel
import com.sopt.now.data.model.ResponseInfoDto
import com.sopt.now.data.model.UserInfo
import com.sopt.now.data.module.ServicePool
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _infoState =
        MutableStateFlow(
            ResponseInfoDto(
                code = 0,
                message = "",
                data = UserInfo(authenticationId = "", nickname = "", phone = "")
            )
        )
    val infoState = _infoState.asStateFlow()

    init {
        fetchInfo()
    }

    private fun fetchInfo() {
        authService.memberInfo().enqueue(object : Callback<ResponseInfoDto> {
            override fun onResponse(
                call: Call<ResponseInfoDto>,
                response: Response<ResponseInfoDto>,
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _infoState.update {
                            data
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseInfoDto>, t: Throwable) {
            }
        })
    }
}