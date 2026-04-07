package com.mrgogu.resono.domain.usecase.auth


import com.mrgogu.resono.domain.model.User
import com.mrgogu.resono.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(uid: String): User?{
        return repository.getUserData(uid)
    }
}