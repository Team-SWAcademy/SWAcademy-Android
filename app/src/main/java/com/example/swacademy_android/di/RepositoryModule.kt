package com.example.swacademy_android.di

import com.example.swacademy_android.data.repository.HomeRepositoryImpl
import com.example.swacademy_android.domain.repository.HomeRepository
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepositoryImpl : HomeRepositoryImpl) : HomeRepository

}