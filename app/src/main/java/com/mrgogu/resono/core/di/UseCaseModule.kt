package com.mrgogu.resono.core.di

import com.mrgogu.resono.domain.repository.AuthRepository
import com.mrgogu.resono.domain.usecase.auth.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(
        repository: AuthRepository
    ): LoginUseCase {
        return LoginUseCase(repository)
    }
}