package com.sopt.now.compose.repository

import com.sopt.now.compose.data.model.ResponseUserDto
import com.sopt.now.compose.data.network.FollowerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FollowerRepository @Inject constructor(
    private val followerService: FollowerService,
) {
    suspend fun getUserList(page: Int): Result<Response<ResponseUserDto>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                followerService.getUserList(page).execute()
            }
        }
    }
}
