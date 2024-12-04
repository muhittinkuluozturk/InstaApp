package com.example.instaapp.ui.login.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.instaapp.data.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(loginViewModel) { user: User ->
                // Handle successful login
                // Navigate to another screen or update UI
            }
        }
        handleOAuthCallback()
    }

    private fun handleOAuthCallback() {
        val data = intent?.data
        if (data != null && data.toString().startsWith("yourapp://auth")) {
            val code = data.getQueryParameter("code")
            if (code != null) {
                // Başarılı giriş
                Log.d("OAuth", "Authorization Code: $code")
                // onLoginSuccess.invoke(code)
            } else {
                // Hata
                Log.e("OAuth", "Authorization failed or canceled")
                // onLoginError.invoke("Error occurred")
            }
        }
    }
}