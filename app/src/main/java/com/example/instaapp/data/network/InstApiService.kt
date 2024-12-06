package com.example.instaapp.data.network

import com.example.instaapp.ui.detail.data.model.InstComment
import com.example.instaapp.ui.detail.data.model.InstPostDetail
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

    @GET("/{post-id}")
    suspend fun getPostDetail(
        @Path("post-id") postId: String,
        @Query("fields") fields: String = "id,caption,media_type,media_url,timestamp,username,comments_count,like_count,user{profile_picture}",
        @Query("access_token") accessToken: String
    ): InstPostDetail

    @GET("/{post-id}/comments")
    suspend fun getPostComments(
        @Path("post-id") postId: String,
        @Query("fields") fields: String = "id,text,username,timestamp,user{profile_picture}",
        @Query("access_token") accessToken: String
    ): List<InstComment>
}