package com.sopt.now.ui.myPage.viewModel

import androidx.lifecycle.ViewModel
import com.sopt.now.data.model.ResponseInfoDto
import com.sopt.now.data.module.ServicePool
import kotlinx.coroutines.flow.MutableStateFlow

class MyPageViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _infoState = MutableStateFlow(ResponseInfoDto)
}