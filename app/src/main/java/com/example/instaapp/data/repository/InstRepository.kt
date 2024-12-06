package com.example.instaapp.data.repository

import com.example.instaapp.ui.detail.data.model.InstComment
import com.example.instaapp.ui.detail.data.model.InstPostDetail
import com.example.instaapp.ui.home.data.model.InstPost

interface InstRepository {
    suspend fun getUserMedia(userId: String, accessToken: String): List<InstPost>

    //TODO Mock data denemeleri için eklendi.
    suspend fun getUserMediaMock(userId: String, accessToken: String): List<InstPost>

    suspend fun getPostDetail(postId: String, accessToken: String): InstPostDetail

    suspend fun getPostComments(postId: String, accessToken: String): List<InstComment>

    //TODO Mock data denemeleri için eklendi.
    suspend fun getPostDetailMock(userId: String, accessToken: String): InstPostDetail
}