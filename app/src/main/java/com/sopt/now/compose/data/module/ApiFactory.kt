package com.sopt.now.compose.data.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.compose.BuildConfig
import com.sopt.now.compose.data.network.FollowerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiFactory {
    private const val FOLLOWER_URL: String = BuildConfig.FOLLOWER_URL
    private val jsonConverterFactory = Json.asConverterFactory("application/json".toMediaType())


    @Provides
    @Singleton
    fun followerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FOLLOWER_URL)
            .addConverterFactory(jsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideFollowerService(followerRetrofit: Retrofit): FollowerService {
        return followerRetrofit.create(FollowerService::class.java)
    }
}