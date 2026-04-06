package com.mrgogu.resono.ui.auth.viewmodel

data class AuthState(
    val isLoading: Boolean = false,
    val user: com.mrgogu.resono.domain.model.User? = null,
    val error: String? = null,
    val isLoggedIn : Boolean =false
)