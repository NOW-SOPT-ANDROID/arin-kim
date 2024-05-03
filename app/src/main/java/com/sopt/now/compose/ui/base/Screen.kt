package com.sopt.now.compose.ui.base

import androidx.annotation.StringRes
import com.sopt.now.compose.R

sealed class Screen(val route: String, val icon: Int, @StringRes val resourceId: Int) {
    object Home : Screen("Home", R.drawable.ic_home_white_24, R.string.screen_home)
    object Search : Screen("Search", R.drawable.ic_search_white_24, R.string.screen_search)
    object MyPage : Screen("MyPage", R.drawable.ic_person_white_24, R.string.screen_my_page)
}

