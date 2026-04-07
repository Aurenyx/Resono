package com.mrgogu.resono.data.datasource.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.mrgogu.resono.domain.model.User
import kotlinx.coroutines.tasks.await

/*  FirebaseAuth instance is injected so this class does not create
    Firebase objects itself. This keeps the architecture flexible
    and allows dependency injection (e.g., Hilt later).
*/
class AuthRemoteDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRemoteDataSource {

/*    Authenticates a user using email and password through Firebase.
    Firebase returns a Task<AuthResult>, which we convert to a
    coroutine-friendly suspend call using await().
    The resulting FirebaseUser represents the authenticated user.
 */
    override suspend fun login(
        email: String,
        password: String
    ): FirebaseUser? {
        val result = firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .await()
        return result.user
    }
    /* Creates a new user account in Firebase using email and password.
     After account creation, we update the user's profile to store
     the display name since Firebase does not automatically save it.
    */
    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): FirebaseUser? {
        val result = firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()
        val user = result.user

        user?.let {
            it.updateProfile(
                UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
            ).await()
            saveUserData(it.uid, name, email)
        }

        return user
    }

    /* Retrieves the currently authenticated user from Firebase.
        If no user session exists, Firebase returns null.
     */
    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    /* Signs out the currently authenticated user from Firebase.
        This clears the authentication session.
 */
    override suspend fun logOut() {
        firebaseAuth.signOut()
    }

    override suspend fun saveUserData(
        uid: String,
        name: String,
        email: String
    ) {
        val userMap = mapOf(
            "uid" to uid,
            "name" to name,
            "email" to email
        )

        firestore.collection("users")
            .document(uid)
            .set(userMap)
            .await()
    }

    override suspend fun getUserData(uid: String): User? {
        val document = firestore.collection("users")
            .document(uid)
            .get()
            .await()

        val data = document.data ?: return null

        return User(
            id = data["uid"] as? String ?: uid,
            name = data["name"] as? String ?: "",
            email = data["email"] as? String ?: ""
        )
    }
}
