package com.sopt.now.ui.home.viewModel

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.Profiles

class HomeViewModel : ViewModel() {
    val friendList = mutableListOf(
        Profiles.MyProfile(
            profileImage = listOf(R.drawable.ic_person_black_24),
            name = "이의경",
            description = "김아린 과제 벼락치기 하지 마라",
            music = "",
            singer = "",
        ),
        Profiles.Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "이의경",
            description = "김아린 과제 벼락치기 하지 마라",
            music = "",
            singer = "",
        ),
    )
}