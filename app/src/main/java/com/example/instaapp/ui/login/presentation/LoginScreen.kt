package com.example.instaapp.ui.login.presentation

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.instaapp.data.model.User

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    onLoginSuccess: (User) -> Unit
) {
    val context = LocalContext.current
    val clientId = "YOUR_CLIENT_ID"
    val redirectUri = "yourapp://auth"
    val authUrl = "https://api.instagram.com/oauth/authorize" +
            "?client_id=$clientId" +
            "&redirect_uri=$redirectUri" +
            "&scope=user_profile,user_media" +
            "&response_type=code"
    val user = loginViewModel.user

    LaunchedEffect(user.value) {
        user.value?.let {
            onLoginSuccess(it)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Instagram Login",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                val customTabsIntent = CustomTabsIntent.Builder().build()
                val uri = Uri.parse(authUrl)
                customTabsIntent.launchUrl(context, uri)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login with Instagram")
        }

        user.value?.let {
            Text(text = "Welcome, ${it.name}")
        }
    }
}
