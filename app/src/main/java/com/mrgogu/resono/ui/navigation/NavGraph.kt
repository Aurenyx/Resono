package com.mrgogu.resono.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mrgogu.resono.ui.auth.viewmodel.AuthViewModel
import com.mrgogu.resono.ui.auth.viewmodel.HomeScreen
import com.mrgogu.resono.ui.auth.viewmodel.LoginScreen
import com.mrgogu.resono.ui.auth.viewmodel.RegisterScreen

@Composable
fun NavGraph(
    viewModel: AuthViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                },
                viewModel = viewModel
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("home") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                viewModel = viewModel
            )
        }

        composable("home") {
            HomeScreen(viewModel = viewModel)
        }
    }
}
