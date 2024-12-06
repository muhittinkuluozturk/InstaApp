package com.example.instaapp.ui.detail.domain

import com.example.instaapp.data.repository.InstRepository
import com.example.instaapp.ui.detail.data.model.InstPostDetail
import javax.inject.Inject

/**
 * Gönderi detayını getiren use case
 */
class GetPostDetailUseCase @Inject constructor(
    private val repository: InstRepository
) {
    suspend operator fun invoke(postId: String, accessToken: String): InstPostDetail {
       // return repository.getPostDetail(postId, accessToken)
        return repository.getPostDetailMock(postId, accessToken)
    }
}
