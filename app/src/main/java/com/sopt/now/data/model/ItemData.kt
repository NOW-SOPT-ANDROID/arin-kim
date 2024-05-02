package com.sopt.now.data.model

import androidx.annotation.DrawableRes

sealed class ItemData {

    data class MyProfile(
        @DrawableRes val profileImage: Int?,
        val name: String,
        val description: String?,
    ) : ItemData()


    data class Friend(
        @DrawableRes val profileImage: Int?,
        val name: String,
        val description: String?,
    ) : ItemData()


}