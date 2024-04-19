package com.sopt.now.compose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.ui.base.Screen
import com.sopt.now.compose.ui.base.SoptBottomNavigation
import com.sopt.now.compose.ui.home.HomeScreen
import com.sopt.now.compose.ui.myPage.MyPageScreen
import com.sopt.now.compose.ui.search.SearchScreen

@Composable
fun MainScreen(
    id: String,
    pw: String,
    nickname: String,
    mbti: String,
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { SoptBottomNavigation(navHostController = navController) }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Search.route) {
                SearchScreen()
            }
            composable(Screen.MyPage.route) {
                MyPageScreen(id, pw, nickname, mbti)
            }
        }
    }
}