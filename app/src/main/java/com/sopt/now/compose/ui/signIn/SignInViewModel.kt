package com.sopt.now.compose.ui.signIn

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.model.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewModel : ViewModel() {

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
}
