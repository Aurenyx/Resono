package com.mrgogu.resono.domain.usecase.auth
import com.mrgogu.resono.domain.model.User
import com.mrgogu.resono.domain.repository.AuthRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val repository: AuthRepository)
{
    suspend operator fun invoke(
        name : String,
        email: String,
        password: String
    ):User? {
        return repository.signUp(name, email , password)
    }
}