package com.sopt.now.compose.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sopt.now.compose.R


@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val followerState by homeViewModel.followerState.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            HomeMyProfile(R.drawable.img_arin, "김아린", "업보 청산의 끝이 보인다.")
        }
        items(followerState) { follower ->
            HomeFriendProfile(
                profileImage = follower.avatar,
                name = "${follower.firstName} ${follower.lastName}",
                description = follower.email,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}