package com.mrgogu.resono.data.datasource.remote
import com.mrgogu.resono.domain.model.User
interface AuthRemoteDataSource {
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