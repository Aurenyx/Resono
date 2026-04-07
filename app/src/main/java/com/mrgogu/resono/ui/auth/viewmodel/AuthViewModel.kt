package com.mrgogu.resono.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrgogu.resono.domain.model.User
import com.mrgogu.resono.domain.usecase.auth.GetCurrentUserUseCase
import com.mrgogu.resono.domain.usecase.auth.GetUserDataUseCase
import com.mrgogu.resono.domain.usecase.auth.LoginUseCase
import com.mrgogu.resono.domain.usecase.auth.LogoutUseCase
import com.mrgogu.resono.domain.usecase.auth.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signupUseCase: SignupUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState(isLoading = true))
    val state: StateFlow<AuthState> = _state

    init {
        checkUserSession()
    }

    private fun checkUserSession() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val firebaseUser = getCurrentUserUseCase()
                val user = firebaseUser?.let {
                    getUserDataUseCase(it.uid) ?: User(
                        id = it.uid,
                        name = it.displayName ?: "",
                        email = it.email ?: ""
                    )
                }
                _state.value = AuthState(
                    user = user,
                    isLoggedIn = user != null
                )
            } catch (e: Exception) {
                _state.value = AuthState(
                    error = e.message ?: "Failed to restore user session."
                )
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthState(isLoading = true)

            try {
                val user = loginUseCase(email, password)
                _state.value = AuthState(
                    user = user,
                    isLoggedIn = user != null,
                    error = if (user == null) "Login failed." else null
                )
            } catch (e: Exception) {
                _state.value = AuthState(
                    error = e.message ?: "Login failed."
                )
            }
        }
    }

    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthState(isLoading = true)

            try {
                val user = signupUseCase(name, email, password)
                _state.value = AuthState(
                    user = user,
                    isLoggedIn = user != null,
                    error = if (user == null) "Sign up failed." else null
                )
            } catch (e: Exception) {
                _state.value = AuthState(
                    error = e.message ?: "Sign up failed."
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                _state.value = AuthState(isLoading = true)
                logoutUseCase()
                _state.value = AuthState()
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Logout failed."
                )
            }
        }
    }
}
