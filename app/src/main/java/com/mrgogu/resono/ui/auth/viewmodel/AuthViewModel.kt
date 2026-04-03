package com.mrgogu.resono.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrgogu.resono.domain.usecase.auth.GetCurrentUserUseCase
import com.mrgogu.resono.domain.usecase.auth.LoginUseCase
import com.mrgogu.resono.domain.usecase.auth.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
    Hilt Injects LoginUseCase automatically , no needs to manually create dependencies
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUseCase: SignupUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    init {
        checkUserSession()
    }

    private fun checkUserSession(){
        val user = getCurrentUserUseCase()
        if (user != null){
            _state.value= AuthState(user = user.email)
        }
    }

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

    fun signUp(name: String, email: String, password: String ){
        viewModelScope.launch {
            _state.value= AuthState(isLoading = true)

            try {
                val user = signUseCase(name, email, password)
                _state.value = AuthState(
                    user = user?.email
                )
            }
            catch (e: Exception){
                _state.value = AuthState(
                    error = e.message
                )
            }
        }
    }

}