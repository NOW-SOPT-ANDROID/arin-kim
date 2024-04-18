package com.sopt.now.ui.home.viewModel

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.Friend

class HomeViewModel : ViewModel() {
    val friendList = listOf(
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "이의경",
            description = "김아린 과제 벼락치기 하지 마라",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "기마린",
            description = "지금.. 업보 청산 중",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "최준서",
            description = "오운완!",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "이연진",
            description = "아리니 넘 귀엽다..",
        ),
        Friend(
            profileImage = R.drawable.ic_person_black_24,
            name = "손민재",
            description = "점심 뭐 먹지?",
        ),
    )
}