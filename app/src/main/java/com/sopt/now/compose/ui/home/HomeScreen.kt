package com.sopt.now.compose.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.now.compose.R
import com.sopt.now.compose.data.Profile

@Composable
fun HomeScreen() {
    val profileList: List<Profile> =
        listOf(
            Profile(
                profileImage = R.drawable.img_profile0,
                name = "의피티",
                description = "김아린 과제 마감 30분전"
            ),
            Profile(
                profileImage = R.drawable.img_profile1,
                name = "최준서",
                description = "오운완 ㅋㅋ",
            ),
            Profile(
                profileImage = R.drawable.img_profile2,
                name = "이연진",
                description = "아리니 넘 기엽당..",
            ),
            Profile(
                profileImage = R.drawable.img_profile3,
                name = "손민재",
                description = "점심 뭐 먹지?",
            ),
            Profile(
                profileImage = R.drawable.img_profile4,
                name = "홍해인",
                description = "난 눈물의 여왕이야",
            ),
            Profile(
                profileImage = R.drawable.img_profile5,
                name = "백현우",
                description = "눈물의여왕시작하지말걸공부가안된다",
            ),
            Profile(
                profileImage = R.drawable.img_profile6,
                name = "이서경",
                description = "저는 환연 과몰입러예요",
            ),
            Profile(
                profileImage = R.drawable.img_profile7,
                name = "이주원",
                description = "너가 자기야 미안해 했잖아?",
            ),
            Profile(
                profileImage = R.drawable.img_profile8,
                name = "김광태",
                description = "내일 뭐 해?",
            ),
            Profile(
                profileImage = R.drawable.img_profile9,
                name = "정현규",
                description = "내봬누",
            ),
            Profile(
                profileImage = R.drawable.img_profile10,
                name = "성해은",
                description = "벌써 스물 아홉이야",
            ),
            Profile(
                profileImage = R.drawable.img_profile11,
                name = "정규민",
                description = "오마카세 사줄게",
            ),
        )

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item { HomeMyProfile(R.drawable.img_arin, "김아린", "업보 청산의 끝이 보인다.") }
        items(profileList.size) { index ->
            HomeFriendProfile(
                profileImage = profileList[index].profileImage,
                name = profileList[index].name,
                description = profileList[index].description,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}