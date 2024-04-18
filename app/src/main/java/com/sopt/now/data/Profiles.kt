package com.sopt.now.data

import androidx.annotation.DrawableRes

sealed class Profiles {
    data class Friend(
        @DrawableRes val profileImage: Int?,
        val name: String,
        val description: String?,
        val singer: String?,
        val music: String?,
    ) : Profiles()

    data class MyProfile(
        @DrawableRes val profileImage: List<Int>?,
        val name: String,
        val description: String?,
        val singer: String?,
        val music: String?,
    ) : Profiles()
}