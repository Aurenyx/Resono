package com.mrgogu.resono.domain.repository
import com.mrgogu.resono.domain.model.User

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String
    ): User
    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): User
    suspend fun getCurrentUser(): User?
    suspend fun logOut()
}