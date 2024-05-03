package com.sopt.now.data.module

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val memberId: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("memberId", memberId)
            .build()
        return chain.proceed(newRequest)
    }
}