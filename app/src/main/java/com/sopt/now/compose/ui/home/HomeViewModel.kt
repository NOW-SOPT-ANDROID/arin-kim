package com.sopt.now.compose.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.model.Profile
import com.sopt.now.compose.data.model.UserDataDto
import com.sopt.now.compose.repository.FollowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val followerRepository: FollowerRepository,
) : ViewModel() {

    private val _followerState = MutableStateFlow<List<UserDataDto>>(emptyList())
    val followerState = _followerState.asStateFlow()

    private var _eventNetworkError = MutableLiveData(false)

    private var _isNetworkErrorShown = MutableLiveData(false)

    private val friendList = mutableListOf<Profile>()

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
                Log.e("HomeError", "${networkError.message}")
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    private fun mapFollowersToFriendList(followers: List<UserDataDto>) {
        friendList.clear()
        friendList.addAll(followers.map { follower ->
            Profile(
                profileImage = follower.avatar,
                name = "${follower.firstName} ${follower.lastName}",
                description = follower.email
            )
        })
    }
}
