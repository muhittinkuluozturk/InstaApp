package com.example.instaapp.ui.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaapp.ui.home.data.model.InstPost
import com.example.instaapp.ui.home.domain.GetUserMediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserMediaUseCase: GetUserMediaUseCase
) : ViewModel() {
    private val _posts = MutableLiveData<List<InstPost>>()
    val posts: LiveData<List<InstPost>> get() = _posts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun loadUserMedia(userId: String, accessToken: String) {
        viewModelScope.launch {
            val result = getUserMediaUseCase(userId, accessToken)
            result.onSuccess { media ->
                _posts.value = media
            }.onFailure { exception ->
                // Hata yönetimi
                _error.value = "Error fetching media: ${exception.message}"
            }
        }
    }
}