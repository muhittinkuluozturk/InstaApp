package com.example.instaapp.ui.home.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instaapp.base.components.PostContent
import com.example.instaapp.base.components.Toolbar
import com.example.instaapp.ui.detail.data.model.InstPostDetail

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onPostClick: (String) -> Unit
) {

    val posts by viewModel.posts.observeAsState(initial = emptyList())
    val error by viewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUserMedia("1", "accessToken")
    }
    Scaffold(
        topBar = { Toolbar() },
        snackbarHost = {
            val snackbarHostState = remember { SnackbarHostState() }
            LaunchedEffect(snackbarHostState) {
                error?.let {
                    snackbarHostState.showSnackbar(it)
                }
            }
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding)
        ) {
            items(posts) { post ->
                if (posts.isNotEmpty()) {
                    val postDetail = InstPostDetail(
                        post,
                        emptyList()
                    )
                    PostContent(postDetail = postDetail,
                        isLiked = postDetail.post.likesCount > 0,
                        onLikeClick = {
                            //NotImplemented
                        },
                        onPostClick = {
                            onPostClick(post.id)
                        })
                }
            }
        }
    }
}


