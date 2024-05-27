package com.example.swacademy_android.di

import com.example.swacademy_android.data.repository.DetailRepositoryImpl
import com.example.swacademy_android.data.repository.HomeRepositoryImpl
import com.example.swacademy_android.data.repository.MypageRepositoryImpl
import com.example.swacademy_android.data.repository.RentalRepositoryImpl
import com.example.swacademy_android.domain.repository.DetailRepository
import com.example.swacademy_android.domain.repository.HomeRepository
import com.example.swacademy_android.domain.repository.MypageRepository
import com.example.swacademy_android.domain.repository.RentalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun bindRentalRepository(rentalRepositoryImpl: RentalRepositoryImpl): RentalRepository

    @Binds
    @Singleton
    abstract fun bindDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository

    @Binds
    @Singleton
    abstract fun bindMypageRepository(mypageRepository: MypageRepositoryImpl): MypageRepository

}