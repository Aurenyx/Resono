package com.mrgogu.resono.core.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
 This tells Hilt:
 "All dependencies provided here will live as long as the app is alive"
 */


/*
Whenever someone asks for FirebaseAuth,
give them FirebaseAuth.getInstance()
 */
@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}