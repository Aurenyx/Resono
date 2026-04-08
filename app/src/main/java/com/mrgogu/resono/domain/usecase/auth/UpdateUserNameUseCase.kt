package com.mrgogu.resono.domain.usecase.auth

import com.mrgogu.resono.domain.repository.AuthRepository
import javax.inject.Inject

class UpdateUserNameUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(uid: String,name: String){
        repository.updateUserName(uid,name)
    }
}