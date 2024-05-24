package com.sopt.now.ui.home.viewModel

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.model.ItemData
import com.sopt.now.data.model.UserData
import com.sopt.now.data.model.UserDataResponse
import com.sopt.now.data.module.ServicePool
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val followerService by lazy { ServicePool.followerService }

    private val _followerState = MutableStateFlow<List<UserData>>(emptyList())
    val followerState = _followerState.asStateFlow()

    val friendList = mutableListOf<ItemData>(
        ItemData.MyProfile(
            profileImage = R.drawable.img_arin,
            name = "김아린",
            description = "업보 청산 중..",
        ),
    )

    init {
        fetchFollowerList()
    }

    private fun fetchFollowerList() {
        followerService.getUserList(page = 0).enqueue(object : Callback<UserDataResponse> {
            override fun onResponse(
                call: Call<UserDataResponse>,
                response: Response<UserDataResponse>,
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    if (data != null) {
                        _followerState.value = data
                        mapFollowersToFriendList(data)
                    }
                }
            }

            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
            }
        })
    }

    fun mapFollowersToFriendList(followers: List<UserData>) {
        friendList.addAll(followers.map { follower ->
            ItemData.Friend(
                profileImage = follower.avatar,
                name = "${follower.firstName} ${follower.lastName}",
                description = follower.email
            )
        })
    }
}