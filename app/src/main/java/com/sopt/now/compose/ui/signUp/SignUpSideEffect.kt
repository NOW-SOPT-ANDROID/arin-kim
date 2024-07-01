package com.sopt.now.compose.ui.signUp

sealed class SignUpSideEffect {
    data class Success(val message: String) : SignUpSideEffect()
    object Loading : SignUpSideEffect()
    data class Error(val message: String) : SignUpSideEffect()
}
