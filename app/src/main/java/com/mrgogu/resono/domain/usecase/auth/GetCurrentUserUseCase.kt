package com.mrgogu.resono.domain.usecase.auth
import com.mrgogu.resono.domain.model.User
import com.mrgogu.resono.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): User? {
        return repository.getCurrentUser()
    }
}
