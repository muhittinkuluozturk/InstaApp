package com.example.instaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instaapp.ui.home.presentation.HomeScreen
import com.example.instaapp.ui.home.presentation.HomeViewModel
import com.example.instaapp.ui.login.presentation.LoginScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           CreateNavContent()
        }
    }

    @Composable
    fun CreateNavContent() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                LoginScreen { user ->
                    navController.navigate("home")
                }
            }

            composable("home") {
                HomeScreen()
            }
        }
    }
}