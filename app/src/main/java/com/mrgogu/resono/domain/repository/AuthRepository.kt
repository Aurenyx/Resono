package com.mrgogu.resono.domain.repository
import com.google.firebase.auth.FirebaseUser
import com.mrgogu.resono.domain.model.User

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String
    ): User?
    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): User?
    fun getCurrentUser(): FirebaseUser?
    suspend fun logOut()
    suspend fun getUserData(uid: String): User?
    suspend fun updateUserName(uid: String, name: String)
}