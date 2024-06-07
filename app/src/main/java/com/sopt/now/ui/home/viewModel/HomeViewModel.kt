package com.sopt.now.ui.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.R
import com.sopt.now.data.model.ItemData
import com.sopt.now.data.model.UserData
import com.sopt.now.repository.FollowerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel : ViewModel() {
    private val followerRepository = FollowerRepository()

    private val _followerState = MutableStateFlow<List<UserData>>(emptyList())
    val followerState = _followerState.asStateFlow()
    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData(false)

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
        viewModelScope.launch {
            try {
                val response = followerRepository.getUserList(0)
                if (response.isSuccessful) {
                    response.body()?.data?.let { data ->
                        _followerState.value = data
                        mapFollowersToFriendList(data)
                    }
                    _eventNetworkError.value = false
                    _isNetworkErrorShown.value = false
                } else {
                    _eventNetworkError.value = true
                }
            } catch (networkError: IOException) {
                _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    private fun mapFollowersToFriendList(followers: List<UserData>) {
        friendList.clear()
        friendList.add(
            ItemData.MyProfile(
                profileImage = R.drawable.img_arin,
                name = "김아린",
                description = "업보 청산 중..",
            ),
        )
        friendList.addAll(followers.map { follower ->
            ItemData.Friend(
                profileImage = follower.avatar,
                name = "${follower.firstName} ${follower.lastName}",
                description = follower.email
            )
        })
    }
}
