package com.example.instaapp.data.repository

import com.example.instaapp.ui.home.data.model.InstPost
import com.example.instaapp.data.network.ApiClient

interface InstRepository {
    suspend fun getUserMedia(userId: String, accessToken: String): List<InstPost>

    //TODO Mock data denemeleri için eklendi.
    suspend fun getUserMediaMock(userId: String, accessToken: String): List<InstPost>
}