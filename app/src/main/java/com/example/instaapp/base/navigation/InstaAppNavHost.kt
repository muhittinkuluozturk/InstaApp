package com.example.instaapp.base.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.instaapp.base.extension.EMPTY
import com.example.instaapp.ui.detail.presentation.PostDetailScreen
import com.example.instaapp.ui.home.presentation.HomeScreen
import com.example.instaapp.ui.login.presentation.LoginScreen

@Composable
fun InstaAppNavHost(
    navController: androidx.navigation.NavHostController = androidx.navigation.compose.rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen { user ->
                navController.navigate("home")
            }
        }

        composable("home") {
            HomeScreen(
                onPostClick = { postId ->
                    navController.navigate("postDetail/$postId")
                }
            )
        }
        composable(
            route = "postDetail/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.StringType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId") ?: EMPTY
            PostDetailScreen(
                postId = postId,
                accessToken = "accessToken",
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}