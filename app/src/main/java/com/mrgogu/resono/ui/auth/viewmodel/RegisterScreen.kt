package com.mrgogu.resono.ui.auth.viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    // UI state
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var localError by remember { mutableStateOf<String?>(null) }

    // Navigate when user is created
    LaunchedEffect(state.user) {
        if (state.user != null) {
            onRegisterSuccess()
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        // Name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(), // 🔒 hide password
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Confirm Password
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // REGISTER BUTTON
        Button(
            onClick = {

                // Reset error
                localError = null

                // Validation
                when {
                    name.isBlank() || email.isBlank() || password.isBlank() -> {
                        localError = "All fields are required"
                    }

                    password.length < 6 -> {
                        localError = "Password must be at least 6 characters"
                    }

                    password != confirmPassword -> {
                        localError = "Passwords do not match"
                    }

                    else -> {
                        // Call ViewModel
                        viewModel.signUp(name, email, password)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // NAVIGATION BUTTON
        TextButton(
            onClick = { onNavigateToLogin() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Already have an account? Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔄 Loading
        if (state.isLoading) {
            CircularProgressIndicator()
        }

        // Show local validation error
        localError?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        // Show backend error (Firebase / ViewModel)
        state.error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}