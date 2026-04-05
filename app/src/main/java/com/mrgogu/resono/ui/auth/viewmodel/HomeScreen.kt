package com.mrgogu.resono.ui.auth.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: AuthViewModel = hiltViewModel()
) {
    Column {
        Text("🚀Welcome to Home Screen✨w")

        Button(onClick = {viewModel.logout()}) {
            Text("Logout")
        }
    }

}