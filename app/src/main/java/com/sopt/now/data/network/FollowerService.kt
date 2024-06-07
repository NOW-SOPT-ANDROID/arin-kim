package com.sopt.now.data.network

import com.sopt.now.data.model.UserDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("/api/users")
    fun getUserList(
        @Query("page") page: Int,
    ): Call<UserDataResponse>
}