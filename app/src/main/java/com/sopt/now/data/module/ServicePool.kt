package com.sopt.now.data.module

import com.sopt.now.data.network.AuthService

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
}