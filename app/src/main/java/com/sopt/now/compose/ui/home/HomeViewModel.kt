package com.sopt.now.compose.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.model.Profile
import com.sopt.now.compose.data.model.ResponseUserDto
import com.sopt.now.compose.data.model.UserDataDto
import com.sopt.now.compose.data.module.ServicePool
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val followerService by lazy { ServicePool.followerService }

    private val _followerState = MutableStateFlow<List<UserDataDto>>(emptyList())
    val followerState = _followerState.asStateFlow()

    val friendList = mutableListOf<Profile>()

    init {
        fetchFollowerList()
    }

    private fun fetchFollowerList() {
        followerService.getUserList(page = 0).enqueue(object : Callback<ResponseUserDto> {
            override fun onResponse(
                call: Call<ResponseUserDto>,
                response: Response<ResponseUserDto>,
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    if (data != null) {
                        _followerState.value = data
                        mapFollowersToFriendList(data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUserDto>, t: Throwable) {
                Log.e("HomeError", "${t.message}")
            }
        })
    }

    fun mapFollowersToFriendList(followers: List<UserDataDto>) {
        for (follower in followers) {
            friendList.add(
                Profile(
                    profileImage = follower.avatar,
                    name = "${follower.firstName} ${follower.lastName}",
                    description = follower.email
                )
            )
        }
    }
}