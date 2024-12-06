package com.example.instaapp.base.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.instaapp.base.extension.TimeFormater
import com.example.instaapp.ui.detail.data.model.InstComment
import com.example.instagramcloneapp.R

@Composable
fun CommentItem(comment: InstComment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_16), vertical = dimensionResource(id = R.dimen.padding_4))
    ) {
        CommentProfilePicture(comment.userProfilePicture)
        Spacer(modifier = Modifier.width( dimensionResource(id = R.dimen.size_8)))
        Column {
            CommentHeader(comment.username, comment.text)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_4)))
            CommentTimestamp(comment.timestamp)
        }
    }
}

@Composable
fun CommentProfilePicture(profilePictureUrl: String) {
    AsyncImage(
        model = profilePictureUrl,
        contentDescription = "Commenter Profile Picture",
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.size_32))
            .clip(CircleShape)
    )
}

@Composable
fun CommentHeader(username: String, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = username,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_8))
        )
        Text(
            text = text,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun CommentTimestamp(timestamp: String) {
    Text(
        text = TimeFormater.formatTimestamp(timestamp),
        color = Color.Gray,
        fontSize = 12.sp
    )
}