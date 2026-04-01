package com.mrgogu.resono.ui.auth.viewmodel

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegisterScreen(
    onRegisterSuccess: ()-> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
}