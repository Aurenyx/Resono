    package com.mrgogu.resono.data.datasource.remote

    import com.google.firebase.auth.FirebaseUser

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
    }