package com.example.instaapp.ui.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaapp.ui.detail.data.model.InstPostDetail
import com.example.instaapp.ui.detail.domain.GetPostCommentsUseCase
import com.example.instaapp.ui.detail.domain.GetPostDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostDetailUseCase: GetPostDetailUseCase,
    private val getPostCommentsUseCase: GetPostCommentsUseCase
) : ViewModel() {

    private val _postDetail = MutableStateFlow<InstPostDetail?>(null)
    val postDetail: StateFlow<InstPostDetail?> = _postDetail

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLiked = MutableStateFlow(false)
    val isLiked: StateFlow<Boolean> = _isLiked

    fun loadPostDetail(postId: String, accessToken: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val postDetail = getPostDetailUseCase(postId, accessToken)
                //TODO getPostDetailUseCase su an mock data donuyor bu sebeple kapatıldı. API sonrası acılacak.
                //val comments = getPostCommentsUseCase(postId, accessToken)
                //_postDetail.value = postDetail.copy(comments = comments)
                _postDetail.value = postDetail
                _isLiked.value = postDetail.post.likesCount != 0
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
}