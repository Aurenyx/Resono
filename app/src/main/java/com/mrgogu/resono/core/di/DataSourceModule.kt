package com.mrgogu.resono.core.di

import com.google.firebase.auth.FirebaseAuth
import com.mrgogu.resono.data.datasource.remote.AuthRemoteDataSource
import com.mrgogu.resono.data.datasource.remote.AuthRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
AuthRemoteDataSource = AuthRemoteDataSourceImpl(firebaseAuth)
 */
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(
        firebaseAuth: FirebaseAuth
    ): AuthRemoteDataSource {
        return AuthRemoteDataSourceImpl(firebaseAuth)
    }
}