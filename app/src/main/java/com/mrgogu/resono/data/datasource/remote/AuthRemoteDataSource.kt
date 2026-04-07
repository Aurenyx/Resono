package com.mrgogu.resono.data.datasource.remote

import com.google.firebase.auth.FirebaseUser
import com.mrgogu.resono.domain.model.User

interface AuthRemoteDataSource {
    suspend fun login(
        email: String,
        password: String
    ): FirebaseUser?

    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): FirebaseUser?

    fun getCurrentUser(): FirebaseUser?

    suspend fun logOut()

    suspend fun saveUserData(
        uid: String,
        name: String,
        email: String
    )

    suspend fun getUserData(uid: String): User?
}
