package com.example.instaapp.ui.detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instaapp.base.components.LoadingIndicator
import com.example.instaapp.base.components.PostContent
import com.example.instaapp.base.components.Toolbar

@Composable
fun PostDetailScreen(
    postId: String,
    accessToken: String,
    viewModel: PostDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val postDetail by viewModel.postDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val isLiked by viewModel.isLiked.collectAsState()

    LaunchedEffect(postId) {
        viewModel.loadPostDetail(postId, accessToken)
    }

    Scaffold(
        topBar = { Toolbar() },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when {
                isLoading -> LoadingIndicator()
                error != null -> ErrorMessage(error!!)
                postDetail != null -> PostContent(
                    postDetail = postDetail!!,
                    isLiked = isLiked,
                    onLikeClick = {
                        //NotImplemented
                    },
                    onPostClick = {
                        //NotImplemented
                    }
                )
            }
        }
    }
}


@Composable
fun ErrorMessage(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: $error", color = Color.Red)
    }
}