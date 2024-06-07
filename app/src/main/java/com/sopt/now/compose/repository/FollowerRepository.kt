package com.sopt.now.compose.repository

import com.sopt.now.compose.data.model.ResponseUserDto
import com.sopt.now.compose.data.module.ServicePool
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class FollowerRepository {
    private val followerService = ServicePool.followerService

    suspend fun getUserList(page: Int): Response<ResponseUserDto> {
        return withContext(Dispatchers.IO) {
            followerService.getUserList(page).execute()
        }
    }
}