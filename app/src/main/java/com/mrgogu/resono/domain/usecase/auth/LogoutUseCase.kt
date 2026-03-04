package com.mrgogu.resono.domain.usecase.auth
import com.mrgogu.resono.domain.model.User
import com.mrgogu.resono.domain.repository.AuthRepository

class LogoutUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke() {
        return repository.logOut()
    }
}