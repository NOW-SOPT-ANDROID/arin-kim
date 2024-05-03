package com.sopt.now.compose.data.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.compose.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit


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

    inline fun <reified T> createBase(): T = baseRetrofit.create(T::class.java)
    inline fun <reified T> createFollower(): T = followerRetrofit.create(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.createBase<AuthService>()
    val followerService = ApiFactory.createFollower<FollowerService>()
}