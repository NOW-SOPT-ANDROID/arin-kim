package com.sopt.now.compose.ui.myPage

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.model.ResponseInfoDto
import com.sopt.now.compose.data.model.UserInfoDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyPageViewModel : ViewModel() {

    private val _infoState =
        MutableStateFlow(
            ResponseInfoDto(
                code = 0,
                message = "",
                data = UserInfoDto(authenticationId = "", nickname = "", phone = "")
            )
        )
    val infoState = _infoState.asStateFlow()
}