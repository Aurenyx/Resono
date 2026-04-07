package com.mrgogu.resono.data.repository

import com.google.firebase.auth.FirebaseUser
import com.mrgogu.resono.domain.model.User
import com.mrgogu.resono.domain.repository.AuthRepository
import com.mrgogu.resono.data.datasource.remote.AuthRemoteDataSource

/*
* This file converts a firebase user into user(domain user),
* so that domain layer should not be in directly in touch with firebase oor any data base we use.
* */
class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource): AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): User? {
        val firebaseUser = remoteDataSource.login(email,password)

        return firebaseUser?.let {
            User(
                id = it.uid,
                name = it.displayName ?:"",
                email = it.email ?:""
            )
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): User?  {
        val firebaseUser = remoteDataSource.signUp(name, email, password)

        return firebaseUser?.let {
            User(
                id = it.uid,
                name = it.displayName ?:"",
                email = it.email ?:""
            )
        }
    }

    override fun getCurrentUser(): FirebaseUser?  {
        return remoteDataSource.getCurrentUser()
    }

    override suspend fun logOut() {
        remoteDataSource.logOut()
    }

    override suspend fun getUserData(uid: String): User? {
        return remoteDataSource.getUserData(uid)
    }
}
