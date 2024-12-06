package com.example.instaapp.base.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.instaapp.base.enums.MediaType
import com.example.instaapp.base.extension.TimeFormater
import com.example.instaapp.ui.detail.data.model.InstComment
import com.example.instaapp.ui.detail.data.model.InstPostDetail
import com.example.instaapp.ui.home.data.model.InstPost
import com.example.instagramcloneapp.R

@Composable
fun PostContent(
    postDetail: InstPostDetail,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    onPostClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        PostHeader(postDetail.post)
        PostMedia(postDetail, onPostClick)
        PostActions(
            likesCount = postDetail.post.likesCount,
            commentsCount = postDetail.post.commentsCount,
            isLiked = isLiked,
            onLikeClick = onLikeClick
        )
        PostCaption(postDetail.post)
        PostComments(postDetail.comments)
    }
}

@Composable
fun PostMedia(postDetail: InstPostDetail, onPostClick: (String) -> Unit) {
    postDetail.post.let { post ->
        when (post.media_type) {
            MediaType.IMAGE.name -> post.media_url?.let { mediaUrl ->
                PostImage(mediaUrl, onClick = { onPostClick(post.id) })
            }
            MediaType.VIDEO.name -> VideoItem(post, onClick = { onPostClick(post.id) })
            MediaType.CAROUSEL_ALBUM.name -> CarouselView(post, onClick = { onPostClick(post.id) })
            else -> { /* Handle other cases or do nothing */ }
        }
    }
}

@Composable
fun PostImage(mediaUrl: String, onClick: () -> Unit) {
    AsyncImage(
        model = mediaUrl,
        contentDescription = "Post Image",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick)
    )
}

@Composable
fun PostActions(
    likesCount: Int,
    commentsCount: Int,
    isLiked: Boolean,
    onLikeClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_16), vertical = dimensionResource(id = R.dimen.padding_8)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onLikeClick) {
            Icon(
                imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Like",
                tint = if (isLiked) Color.Red else Color.Black
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_8)))
        IconButton(onClick = { /* TODO: Implement comment action */ }) {
            Icon(
                imageVector = Icons.Outlined.MailOutline,
                contentDescription = "Comment"
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_8)))
        IconButton(onClick = { /* TODO: Implement share action */ }) {
            Icon(
                imageVector = Icons.Outlined.Share,
                contentDescription = "Share"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* TODO: Implement bookmark action */ }) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = "Bookmark"
            )
        }
    }
    Text(
        text = "$likesCount likes",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_16))
    )
}

@Composable
fun PostCaption(post: InstPost) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_4))
    ) {
        post.username?.let {
            Text(
                text = it,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_4))
            )
        }
        post.caption?.let { Text(text = it) }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_8)))
        Text(
            text = TimeFormater.formatTimestamp(post.timestamp),
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun PostComments(comments: List<InstComment>) {
    comments.forEach { comment ->
        CommentItem(comment)
    }
}