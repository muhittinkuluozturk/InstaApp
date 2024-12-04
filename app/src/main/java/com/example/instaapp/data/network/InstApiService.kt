package com.example.instaapp.data.network

import com.example.instaapp.ui.home.data.model.InstPost
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API servisi InstagramGraphApi için
 */
interface InstApiService {
    @GET("users/{user_id}/media")
    suspend fun getUserMedia(
        @Path("user_id") userId: String,
        @Query("access_token") accessToken: String
    ): List<InstPost>
}