package com.example.instaapp.ui.home.domain

import com.example.instaapp.data.repository.InstRepository
import com.example.instaapp.ui.home.data.model.InstPost
import javax.inject.Inject

/**
 * Anasayfa medialarını getiren usecase
 */
class GetUserMediaUseCase @Inject constructor(
    private val repository: InstRepository
) {
    suspend operator fun invoke(userId: String, accessToken: String): Result<List<InstPost>> {
        return try {
           // val media = repository.getUserMedia(userId, accessToken)
            val media = repository.getUserMediaMock(userId, accessToken)
            Result.success(media)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}