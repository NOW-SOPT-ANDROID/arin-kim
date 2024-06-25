package com.sopt.now.compose.data.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.compose.BuildConfig
import com.sopt.now.compose.data.network.AuthService
import com.sopt.now.compose.data.network.FollowerService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create


object ApiFactory {
    private const val BASE_URL: String = BuildConfig.AUTH_BASE_URL
    private const val FOLLOWER_URL: String = BuildConfig.FOLLOWER_URL

    private val interceptorClient = OkHttpClient().newBuilder()
        .addInterceptor(AuthInterceptor()).build()

    val baseRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(interceptorClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val followerRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(FOLLOWER_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> createBaseRetrofit(): T = baseRetrofit.create()
    inline fun <reified T> createFollowerRetrofit(): T = followerRetrofit.create()
}

object ServicePool {
    val authService = ApiFactory.createBaseRetrofit<AuthService>()
    val followerService = ApiFactory.createFollowerRetrofit<FollowerService>()
}