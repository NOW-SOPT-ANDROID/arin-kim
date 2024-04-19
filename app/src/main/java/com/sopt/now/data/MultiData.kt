package com.sopt.now.data

import androidx.annotation.DrawableRes

sealed class MultiData {

    data class MyProfile(
        @DrawableRes val profileImage: Int?,
        val name: String,
        val description: String?,
    ) : MultiData()


    data class Friend(
        @DrawableRes val profileImage: Int?,
        val name: String,
        val description: String?,
    ) : MultiData()


}