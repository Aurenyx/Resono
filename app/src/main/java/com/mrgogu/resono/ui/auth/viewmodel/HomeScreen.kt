package com.mrgogu.resono.ui.auth.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Column {
        Text("✨Welcome ${state.user?.name}✨")

        Button(onClick = {viewModel.logout()}) {
            Text("Logout")
        }
    }

}