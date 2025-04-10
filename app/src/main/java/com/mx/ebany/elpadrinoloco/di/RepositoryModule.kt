package com.mx.ebany.elpadrinoloco.di

import com.mx.ebany.elpadrinoloco.data.repository.MainRepository
import com.mx.ebany.elpadrinoloco.data.repository.MainRepositoryImplement
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

interface RepositoryModule {
    @Binds
    fun provideMainRepositoryImpl(repository: MainRepositoryImplement): MainRepository
}