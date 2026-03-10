package com.mrgogu.resono.data.datasource.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

// FirebaseAuth instance is injected so this class does not create
// Firebase objects itself. This keeps the architecture flexible
// and allows dependency injection (e.g., Hilt later).
class AuthRemoteDataSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRemoteDataSource {

//    Authenticates a user using email and password through Firebase.
// Firebase returns a Task<AuthResult>, which we convert to a
// coroutine-friendly suspend call using await().
// The resulting FirebaseUser represents the authenticated user.
    override suspend fun login(
        email: String,
        password: String
    ): FirebaseUser? {
        val result = firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .await()
        return result.user
    }

    // Creates a new user account in Firebase using email and password.
// After account creation, we update the user's profile to store
// the display name since Firebase does not automatically save it.
    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): FirebaseUser? {
        val result = firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()
        val user = result.user

        user?.updateProfile(
            com.google.firebase.auth.UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()
        )?.await()

        return user
    }

    // Retrieves the currently authenticated user from Firebase.
// If no user session exists, Firebase returns null.
    override suspend fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    // Signs out the currently authenticated user from Firebase.
// This clears the authentication session.
    override suspend fun logOut() {
        firebaseAuth.signOut()
    }
}