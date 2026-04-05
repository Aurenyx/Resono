package com.mrgogu.resono.domain.usecase.auth
import com.mrgogu.resono.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor
    (private val repository: AuthRepository)
{
    suspend operator fun invoke() {
        return repository.logOut()
    }
}