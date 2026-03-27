package com.mrgogu.resono.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.mrgogu.resono.ui.auth.viewmodel.HomeScreen
import com.mrgogu.resono.ui.auth.viewmodel.LoginScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController =  navController,
        startDestination = "login"
     ) {


        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("home") {
            HomeScreen()
        }
    }
}