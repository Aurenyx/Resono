package com.mrgogu.resono.domain.usecase.auth
import com.mrgogu.resono.domain.model.User
import com.mrgogu.resono.domain.repository.AuthRepository

class LoginUseCase( private val repository: AuthRepository) {
    // Here invoke function calls the object of User class and using suspend function.
    suspend operator fun invoke(
        email: String,
        password: String
    ):User? {
        return repository.login(email,password)
    }
}