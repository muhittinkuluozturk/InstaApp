package com.example.instaapp.data.repository

import com.example.instaapp.ui.home.data.model.InstPost
import com.example.instaapp.data.network.ApiClient

class InstRepository {
    private val apiService = ApiClient.apiService

    suspend fun getUserMedia(userId: String, accessToken: String): List<InstPost> {
        return apiService.getUserMedia(userId, accessToken)
    }
}