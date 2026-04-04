package com.mrgogu.resono.ui.auth.viewmodel

data class AuthState(
    val isLoading: Boolean = false,
    val user: String? = null,
    val error: String? = null,
    val isLoggedIn : Boolean =false
)