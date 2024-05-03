package com.sopt.now.compose.data.network

import com.sopt.now.compose.data.model.ResponseUserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("/api/users")
    fun getUserList(
        @Query("page") page: Int,
    ): Call<ResponseUserDto>
}