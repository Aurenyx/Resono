package com.mrgogu.resono.domain.usecase.auth
import com.google.firebase.auth.FirebaseUser
import com.mrgogu.resono.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): FirebaseUser? {
        return repository.getCurrentUser()
    }
}
