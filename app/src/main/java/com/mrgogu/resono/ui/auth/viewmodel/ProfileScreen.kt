package com.mrgogu.resono.ui.auth.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProfileScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    var name by remember { mutableStateOf(state.user?.name ?: "") }

    Column {

        Text(text = "Profile")

        Text(text = "Email: ${state.user?.email}")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it},
            label = { Text("Name")}
        )


    }
}