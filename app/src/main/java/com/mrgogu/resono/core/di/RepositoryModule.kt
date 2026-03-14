package com.mrgogu.resono.core.di

import com.mrgogu.resono.data.repository.AuthRepositoryImpl
import com.mrgogu.resono.domain.repository.AuthRepository
import com.mrgogu.resono.data.datasource.remote.AuthRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
AuthRepository → AuthRepositoryImpl
 */


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(
        remoteDataSource: AuthRemoteDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(remoteDataSource)
    }
}