package com.sopt.now.repository

import com.sopt.now.data.model.UserDataResponse
import com.sopt.now.data.module.ServicePool
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class FollowerRepository {
    private val followerService = ServicePool.followerService

    suspend fun getUserList(page: Int): Response<UserDataResponse> {
        return withContext(Dispatchers.IO) {
            followerService.getUserList(page).execute()
        }
    }
}
