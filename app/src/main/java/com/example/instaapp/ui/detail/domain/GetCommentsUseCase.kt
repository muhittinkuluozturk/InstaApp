package com.example.instaapp.ui.detail.domain

import com.example.instaapp.data.repository.InstRepository
import com.example.instaapp.ui.detail.data.model.InstComment
import javax.inject.Inject

/**
 * Bir postun yorumlarını getiren use case
 */
class GetPostCommentsUseCase @Inject constructor(
    private val repository: InstRepository
) {
    suspend operator fun invoke(postId: String, accessToken: String): List<InstComment> {
        return repository.getPostComments(postId, accessToken)
    }
}