package com.sopt.now.compose.ui.base

import androidx.annotation.StringRes
import com.sopt.now.compose.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("Home", R.string.screen_home)
    object Search : Screen("Search", R.string.screen_search)
    object MyPage : Screen("MyPage", R.string.screen_my_page)
}

