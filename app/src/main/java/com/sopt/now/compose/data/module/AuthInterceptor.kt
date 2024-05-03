package com.sopt.now.compose.data.module

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("memberId", "514")
            .build()

        return chain.proceed(newRequest)
    }
}