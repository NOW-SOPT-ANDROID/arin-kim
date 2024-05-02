package com.sopt.now.data.network

import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.data.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>
}