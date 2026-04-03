package com.mrgogu.resono.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.mrgogu.resono.ui.auth.viewmodel.HomeScreen
import com.mrgogu.resono.ui.auth.viewmodel.LoginScreen
import com.mrgogu.resono.ui.auth.viewmodel.RegisterScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        //LOGIN SCREEN
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true } // remove login from backstack
                    }
                },
                // 🔁 Go to Register
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }

        // REGISTER SCREEN
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("home") {
                        popUpTo("register") { inclusive = true }
                    }
                },

                // 🔙 Back to Login
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        //HOME SCREEN
        composable("home") {
            HomeScreen()
        }
    }
}