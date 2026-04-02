package com.mrgogu.resono.ui.auth.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(
    onRegisterSuccess: ()-> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(state.user) {
        if(state.user != null){
            onRegisterSuccess()
        }
    }

    Column( modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = { Text("Name")}
        )
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text("Email")}
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Password")}
        )

        Button(onClick = {
                viewModel.signUp(name, email,password) }
        ) {
            Text("Register")
        }
    }
}