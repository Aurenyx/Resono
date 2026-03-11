package com.mrgogu.resono.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrgogu.resono.domain.model.User
import com.mrgogu.resono.domain.usecase.auth.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel (){

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun login(email: String, password: String){

        viewModelScope.launch {
            val result = loginUseCase(email, password)
            _user.value = result
        }
    }
}