package com.sopt.now.ui.home.viewModel

import androidx.lifecycle.ViewModel
import com.sopt.now.R
import com.sopt.now.data.MultiData

class HomeViewModel : ViewModel() {
    val friendList = mutableListOf(

        MultiData.MyProfile(
            profileImage = R.drawable.img_arin,
            name = "김아린",
            description = "업보 청산 중..",
        ),
        MultiData.Friend(
            profileImage = R.drawable.img_profile0,
            name = "이의경",
            description = "김아린 과제 벼락치기 하지 마라",
        ),
        MultiData.Friend(
            profileImage = R.drawable.img_profile1,
            name = "최준서",
            description = "오운완 ㅋㅋ",
        ),
        MultiData.Friend(
            profileImage = R.drawable.img_profile2,
            name = "이연진",
            description = "아리니 넘 기엽당..",
        ),
        MultiData.Friend(
            profileImage = R.drawable.img_profile3,
            name = "손민재",
            description = "점심 뭐 먹지?",
        ),
        MultiData.Friend(
            profileImage = R.drawable.img_profile4,
            name = "홍해인",
            description = "난 눈물의 여왕이야",
        ),
        MultiData.Friend(
            profileImage = R.drawable.img_profile5,
            name = "백현우",
            description = "눈물의여왕시작하지말걸공부가안된다",
        ),
        MultiData.Friend(
            profileImage = R.drawable.img_profile6,
            name = "이서경",
            description = "저는 환연 과몰입러예요",
        ),
        MultiData.Friend(
            profileImage = R.drawable.img_profile7,
            name = "이주원",
            description = "너가 자기야 미안해 했잖아?",
        ),
        MultiData.Friend(
            profileImage = R.drawable.img_profile8,
            name = "김광태",
            description = "내일 뭐 해?",
        ),
        MultiData.Friend(
            profileImage = R.drawable.img_profile9,
            name = "정현규",
            description = "내봬누",
        ),
        MultiData.Friend(
            profileImage = R.drawable.img_profile10,
            name = "성해은",
            description = "벌써 스물 아홉이야",
        ),
        MultiData.Friend(
            profileImage = R.drawable.img_profile11,
            name = "정규민",
            description = "오마카세 사줄게",
        ),
    )
}