package com.mrgogu.resono.ui.auth.viewmodel


import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.mrgogu.resono.ui.navigation.NavGraph

@Composable
fun AuthGate(
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }

        state.isLoggedIn -> {
            // ✅ User already logged in
            HomeScreen()
        }

        else -> {
            // ❌ Not logged in → show login/register flow
            NavGraph()
        }
    }
}