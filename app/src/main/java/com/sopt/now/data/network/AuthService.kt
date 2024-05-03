package com.sopt.now.data.network

import com.sopt.now.data.model.RequestSignInDto
import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.data.model.ResponseInfoDto
import com.sopt.now.data.model.ResponseSignInDto
import com.sopt.now.data.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>

    @POST("member/login")
    fun signIn(
        @Body request: RequestSignInDto,
    ): Call<ResponseSignInDto>

    @GET("member/info")
    fun memberInfo(
    ): Call<ResponseInfoDto>
}