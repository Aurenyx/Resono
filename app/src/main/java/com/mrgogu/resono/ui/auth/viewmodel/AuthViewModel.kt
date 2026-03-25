package com.mrgogu.resono.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrgogu.resono.domain.usecase.auth.LoginUseCase
import com.mrgogu.resono.ui.auth.viewmodel.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthState(isLoading = true)

            try {
                val user = loginUseCase(email, password)
                _state.value = AuthState(
                    user = user?.email
                )
            } catch (e: Exception) {
                _state.value = AuthState(
                    error = e.message
                )
            }
        }
    }
}