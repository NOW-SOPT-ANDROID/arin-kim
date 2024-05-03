package com.sopt.now.compose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserDto(
    val page: Int,
    @SerialName("per_page") val perPage: Int,
    val total: Int,
    @SerialName("total_pages") val totalPages: Int,
    val data: List<UserData>,
    val support: Support,
)

@Serializable
data class UserData(
    val id: Int,
    val email: String,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    val avatar: String,
)

@Serializable
data class Support(
    val url: String,
    val text: String,
)