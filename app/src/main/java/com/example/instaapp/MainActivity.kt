package com.example.instaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instaapp.ui.login.presentation.LoginScreen
import com.example.instaapp.ui.login.presentation.LoginViewModel
import com.example.instaapp.data.model.User
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(loginViewModel) { user: User ->
                // Handle successful login
                // Navigate to another screen or update UI
            }
        }
    }
}