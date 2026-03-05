package com.mrgogu.resono.data.repository

import com.mrgogu.resono.domain.model.User
import com.mrgogu.resono.domain.repository.AuthRepository
import com.mrgogu.resono.data.datasource.remote.AuthRemoteDataSource

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource): AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): User {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): User {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): User? {
        TODO("Not yet implemented")
    }

    override suspend fun logOut() {
        TODO("Not yet implemented")
    }
}